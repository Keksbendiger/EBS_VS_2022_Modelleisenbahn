package core;

import mqtt.MqttClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrackSectionEnterRequest {
    private static List<TrackSectionEnterRequest> requests = new ArrayList<>();

    private Train train;
    private TrackSection leaving;
    private TrackSection entering;
    private LocalDateTime requestTime;
    private EEnterRequestState state;

    public static void advanceRequest(TrackSectionEnterRequest request, boolean isIngoing, int count) {
        if (count != request.train.getNumLength()) {
            System.err.println("Wrong Axis Count on " + request.train.getIdentifier() + ". Expected: " + request.train.getNumLength() + " | Counted: " + count);
            MqttClient.getInstance().emergencyStopAllTrains();
            return;
        }
        switch (request.state) {
            case PERMITTED:
                // Train was allowed to move - now it should first have triggered an outgoing sensor
                if (isIngoing) // ERROR - wrong sensor order
                    System.err.println("Train (" + request.train.getIdentifier() + ") movement was permitted, but sensors were activated in the wrong order");
                // in practice it has left the 'leaving' track - but we consider it moving onto the 'entering' track
                if (request.entering.isBlocked()) {
                    System.err.println("Train " + request.train.getIdentifier() + " tried to move to a blocked section");
                }
                request.entering.block(request.train);
                request.state = EEnterRequestState.LEFT_OLD;
                break;
            case LEFT_OLD:
                // Train had already triggered the leaving sensor and should now have triggered the entering one
                if (!isIngoing) // ERROR - wrong sensor order
                    System.err.println("Train " + request.train.getIdentifier() + " tried to move to a blocked section");
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
                if (nextTarget.isBlocked()) {
                    request.train.block();
                }
                break;
            default:
                System.err.println("Train moving without permission: " + request.train.getIdentifier());
                // this should not be triggered in any other case
        }
    }

    public static void checkRequestPermissions(TrackSection freedSection) {
        if (freedSection.isBlocked()) return;
        for (TrackSectionEnterRequest request : requests) {
            if (request.entering == freedSection && !request.isPermitted()) {
                request.permit();
                return;
            }
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
        checkRequestPermissions(entering);
    }

    public boolean isPermitted() {
        return this.state != EEnterRequestState.WAITING;
    }

    public void permit() {
        if (this.entering.getIdentifier() == ETrackSection.B || this.entering.getIdentifier() == ETrackSection.G) {
            this.state = EEnterRequestState.LEFT_OLD;
        } else {
            this.state = EEnterRequestState.PERMITTED;
        }
        TrackSwitch ts = TrackSwitch.get(this.leaving.getIdentifier(), this.entering.getIdentifier());
        if (ts != null) {
            ts.switchToSection(this.entering, this.leaving);
            System.out.println("Switching " + ts.getIdentifier().toString() + " to " + this.entering.getIdentifier().toString() + " / " + this.leaving.getIdentifier().toString());
        }
        this.train.unblock();
    }
}
