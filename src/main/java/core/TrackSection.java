package core;

import gui.GuiHandler;

import java.util.HashMap;
import java.util.Map;

public class TrackSection {
    public static Map<ETrackSection, TrackSection> sections = new HashMap<>();

    public static TrackSection get(ETrackSection identifier) {
        return sections.get(identifier);
    }
    public static boolean isFree(ETrackSection identifier) {
        TrackSection section = get(identifier);
        return !section.isBlocked();
    }

    private final ETrackSection identifier;
    private final double delay;
    private boolean isBlocked;
    private Train train = null;

    public TrackSection(ETrackSection identifier, double delay) {
        this.identifier = identifier;
        this.delay = delay;
        this.isBlocked = false;

        sections.put(identifier, this);
    }

    public void free() {
        if(this.isBlocked) {
            this.isBlocked = false;
            this.train = null;
            GuiHandler.getInstance().setTrackSectionUsed(identifier, false);
        }
    }

    public void block(Train train) {
        if(!this.isBlocked) {
            this.isBlocked = true;
            this.train = train;
            GuiHandler.getInstance().setTrackSectionUsed(identifier, true, this.train.toString());
        }
    }

    public boolean isBlocked() {
        return this.isBlocked;
    }

    public ETrackSection getIdentifier() {
        return identifier;
    }

    public Train getTrain() {
        return train;
    }

    public double getDelay() {
        return delay;
    }

    @Override
    public String toString() {
        return identifier.name();
    }
}
