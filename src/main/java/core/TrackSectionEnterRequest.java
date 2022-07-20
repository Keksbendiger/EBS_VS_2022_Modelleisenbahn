package core;

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
        if(count != request.train.getNumLength()) {
            // ERROR wrong train length
        }
        switch(request.state) {
            case PERMITTED:
                // Train was allowed to move - now it should first have triggered an outgoing sensor
                if(isIngoing) // ERROR - wrong sensor order
                // in practice it has left the 'leaving' track - but we consider it moving onto the 'entering' track
                if(request.entering.isBlocked()) {
                    // ERROR
                }
                request.entering.block(request.train);
                request.state = EEnterRequestState.LEFT_OLD;
                break;
            case LEFT_OLD:
                // Train had already triggered the leaving sensor and should now have triggered the entering one
                if(!isIngoing) // ERROR - wrong sensor order
                // we consider it having fully left the old track now
                request.leaving.free();
                request.state = EEnterRequestState.ENTERED_NEW;

                // we can close the request now
                break;
            default:
                // ERROR
                // this should not be triggered in any other case
        }
    }

    public static TrackSectionEnterRequest getRequest(ETrackSection from, ETrackSection to) {
        for (TrackSectionEnterRequest request : requests) {
            if(request.leaving.getIdentifier() == from && request.entering.getIdentifier() == to) {
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
    }

    public boolean isPermitted() {
        return this.state != EEnterRequestState.WAITING;
    }
}
