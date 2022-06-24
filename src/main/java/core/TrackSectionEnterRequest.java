package core;

import java.time.LocalDateTime;

public class TrackSectionEnterRequest {
    private Train train;
    private TrackSection leaving;
    private TrackSection entering;
    private LocalDateTime requestTime;
}
