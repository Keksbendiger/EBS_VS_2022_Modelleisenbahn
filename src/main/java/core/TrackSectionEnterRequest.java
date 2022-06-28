package core;

import java.time.LocalDateTime;

public class TrackSectionEnterRequest {
    private Train train;
    private TrackSection leaving;
    private TrackSection entering;
    private LocalDateTime requestTime;

    public TrackSectionEnterRequest(Train train, TrackSection leaving, TrackSection entering) {
        this.train = train;
        this.leaving = leaving;
        this.entering = entering;
        this.requestTime = LocalDateTime.now();
    }

    public void accept() {
        entering.setBlocked(true);
    }
}
