package core;

public class TrackSection {
    private final String identifier;
    private final double delay;
    private boolean isBlocked;

    public TrackSection(String identifier, double delay) {
        this.identifier = identifier;
        this.delay = delay;
        this.isBlocked = false;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean isBlocked() {
        return this.isBlocked;
    }
}
