package core;

import util.Logger;

//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  EBS_VS_2022_Modelleisenbahn
// created:  20.07.2022
//--------------------------------------------------//
public class RouteManager {
    private static RouteManager instance = null;

    public static RouteManager getInstance() {
        if(instance == null) {
            instance = new RouteManager();
        }
        return instance;
    }

    public ETrackSection nextTarget(ETrackSection old, ETrackSection current) {
        if(old == ETrackSection.A && current == ETrackSection.B) return ETrackSection.C;
        if(old == ETrackSection.B && current == ETrackSection.C) return ETrackSection.D;
        if(old == ETrackSection.C && current == ETrackSection.D) return ETrackSection.E;
        if(old == ETrackSection.C && current == ETrackSection.H) return ETrackSection.E;
        if(old == ETrackSection.D && current == ETrackSection.E) return ETrackSection.F;
        if(old == ETrackSection.H && current == ETrackSection.E) return ETrackSection.F;
        if(old == ETrackSection.E && current == ETrackSection.F) return ETrackSection.A;
        if(old == ETrackSection.E && current == ETrackSection.I) return ETrackSection.A;
        if(old == ETrackSection.F && current == ETrackSection.A) return ETrackSection.B;
        if(old == ETrackSection.I && current == ETrackSection.A) return ETrackSection.B;

        if(old == ETrackSection.A && current == ETrackSection.F) return ETrackSection.E;
        if(old == ETrackSection.A && current == ETrackSection.I) return ETrackSection.E;
        if(old == ETrackSection.F && current == ETrackSection.E) return ETrackSection.D;
        if(old == ETrackSection.I && current == ETrackSection.E) return ETrackSection.D;
        if(old == ETrackSection.E && current == ETrackSection.D) return ETrackSection.C;
        if(old == ETrackSection.H && current == ETrackSection.D) return ETrackSection.C;
        if(old == ETrackSection.D && current == ETrackSection.C) return ETrackSection.B;
        if(old == ETrackSection.H && current == ETrackSection.C) return ETrackSection.B;
        if(old == ETrackSection.C && current == ETrackSection.B) return ETrackSection.A;
        if(old == ETrackSection.B && current == ETrackSection.A) return ETrackSection.F;

        Logger.err("Tried to find next target for " + old.toString() + " -> " + current.toString() + " but none is defined");
        return null;
    }

    public ETrackSection findAlternative(ETrackSection section) {
        switch (section) {
            case F:
                return ETrackSection.I;
            case I:
                return ETrackSection.F;
            case D:
                return ETrackSection.H;
            case H:
                return ETrackSection.D;
            default:
                return section;
        }
    }

    public ETrackSection nextTargetOrAlternative(ETrackSection old, ETrackSection current) {
        ETrackSection nextTarget = nextTarget(old, current);
        ETrackSection alternative;
        if(TrackSection.get(nextTarget).isBlocked()) {
            alternative = findAlternative(nextTarget);
            if(!TrackSection.get(alternative).isBlocked()) {
                Logger.log("Next Target: " + old.toString() + " -> " + current.toString() + " -> " + nextTarget.toString() + " |-> " + alternative.toString());
                return alternative;
            }
        }
        Logger.log("Next Target: " + old.toString() + " -> " + current.toString() + " -> " + nextTarget.toString() + (TrackSection.get(nextTarget).isBlocked()? " (blocked)": " (free)"));
        return nextTarget;
    }
}
