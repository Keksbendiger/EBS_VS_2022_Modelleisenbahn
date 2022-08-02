package core;

import mqtt.MqttClient;
import util.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrackSectionEnterRequest {
    private static List<TrackSectionEnterRequest> requests = new ArrayList<>();
    public static boolean shutdown = false;

    private Train train;
    private TrackSection leaving;
    private TrackSection entering;
    private LocalDateTime requestTime;
    private EEnterRequestState state;

    public static void advanceRequest(TrackSectionEnterRequest request, boolean isIngoing, int count) {
        if(shutdown) return;

        if (count != request.train.getNumLength()) {
            Logger.err("Wrong Axis Count on " + request.train + ". Expected: " + request.train.getNumLength() + " | Counted: " + count);
            MqttClient.getInstance().emergencyStopAllTrains();
            return;
        }
        Logger.log("Advance Request: " + request.train + " going to " + request.entering + " with state: " + request.state.toString());
        switch (request.state) {
            case PERMITTED:
                // Train was allowed to move - now it should first have triggered an outgoing sensor
                // in practice it has left the 'leaving' track - but we consider it moving onto the 'entering' track
                if (request.entering.isBlocked() && request.entering.getTrain().getTrainId() != request.train.getTrainId()) {
                    Logger.err("Train " + request.train + " tried to move to blocked section " + request.entering);
                    MqttClient.getInstance().emergencyStopAllTrains();
                    return;
                }
                request.state = EEnterRequestState.LEFT_OLD;
                break;
            case LEFT_OLD:
                // Train had already triggered the leaving sensor and should now have triggered the entering one
                // we consider it having fully left the old track now
                request.leaving.free();
                // we can now check if there are any requests that want to use the section we just freed
                checkRequestPermissions(request.leaving);

                // maybe completely remove that state
                request.state = EEnterRequestState.ENTERED_NEW;

                // we can close the request now
                // but we also need to create the follow request
                TrackSection nextTarget = TrackSection.get(RouteManager.getInstance().nextTargetOrAlternative(request.leaving.getIdentifier(), request.entering.getIdentifier()));
                requests.remove(request);
                new TrackSectionEnterRequest(request.train, request.entering, nextTarget);
                // check if nextTarget track is free
                if (nextTarget.isBlocked() && nextTarget.getTrain().getTrainId() != request.train.getTrainId()) {
                    request.train.block();
                }
                break;
            default:
                Logger.err("Train moving without permission: " + request.train);
                // this should not be triggered in any other case
        }
    }

    public static void checkRequestPermissions(TrackSection freedSection) {
        // TODO maybe also check requests for alternatives
        if (freedSection.isBlocked()) return;
        TrackSectionEnterRequest validRequest = null;
        for (TrackSectionEnterRequest request : requests) {
            if (request.entering == freedSection && !request.isPermitted()) {
                if(validRequest == null) {
                    validRequest = request;
                } else {
                    if(request.train.getPriority() > validRequest.train.getPriority()) {
                        validRequest = request;
                    }
                }
            }
        }
        if(validRequest != null) {
            validRequest.permit();
        }
    }

    public static TrackSectionEnterRequest getRequest(ETrackSection from, ETrackSection to) {
        for (TrackSectionEnterRequest request : requests) {
            if (request.leaving.getIdentifier() == from && request.entering.getIdentifier() == to) {
                return request;
            }
        }
        return null;
    }

    public TrackSectionEnterRequest(Train train, TrackSection leaving, TrackSection entering) {
        this.train = train;
        this.leaving = leaving;
        this.entering = entering;
        this.requestTime = LocalDateTime.now();
        this.state = EEnterRequestState.WAITING;

        requests.add(this);
        Logger.log("New Request [" + train + "] from (" + leaving + ") to (" + entering + ")");
        checkRequestPermissions(entering);
    }

    public boolean isPermitted() {
        return this.state != EEnterRequestState.WAITING;
    }

    public void permit() {
        if (this.entering.getIdentifier() == ETrackSection.B || this.entering.getIdentifier() == ETrackSection.G
        || this.leaving.getIdentifier() == ETrackSection.B /*DIRTY FIX MISSING SENSOR TODO REMOVE*/|| this.entering.getIdentifier() == ETrackSection.A || this.leaving.getIdentifier() == ETrackSection.A /*TODO REMOVE*/) {
            this.state = EEnterRequestState.LEFT_OLD;
        } else {
            this.state = EEnterRequestState.PERMITTED;
        }
        TrackSwitch ts = TrackSwitch.get(this.leaving.getIdentifier(), this.entering.getIdentifier());
        if (ts != null) {
            // always switch ONE and TWO together
            if (ts.getIdentifier() == ETrackSwitch.ONE || ts.getIdentifier() == ETrackSwitch.TWO) {
                if ((entering.getIdentifier() == ETrackSection.B || entering.getIdentifier() == ETrackSection.G)
                        && (leaving.getIdentifier() == ETrackSection.B || leaving.getIdentifier() == ETrackSection.G)) {
                    TrackSwitch.get(ETrackSwitch.ONE).switchToSection(TrackSection.get(ETrackSection.G), TrackSection.get(ETrackSection.B));
                    TrackSwitch.get(ETrackSwitch.TWO).switchToSection(TrackSection.get(ETrackSection.G), TrackSection.get(ETrackSection.B));
                    Logger.log("Switching ONE and TWO to G");
                } else {
                    TrackSwitch.get(ETrackSwitch.ONE).switchToSection(TrackSection.get(ETrackSection.A), TrackSection.get(ETrackSection.B));
                    TrackSwitch.get(ETrackSwitch.TWO).switchToSection(TrackSection.get(ETrackSection.C), TrackSection.get(ETrackSection.B));
                    Logger.log("Switching ONE and TWO to A and C");
                }
            } else {
                ts.switchToSection(this.entering, this.leaving);
                Logger.log("Switching " + ts + " to " + this.entering.toString() + " / " + this.leaving);
            }
        }
        Logger.log("Permitted " + train + " to go to " + entering);
        entering.block(this.train);
        this.train.unblock();
    }
}
