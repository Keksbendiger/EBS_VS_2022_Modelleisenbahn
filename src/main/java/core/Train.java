package core;

public class Train {
    public enum TrainState {MOVING_CLOCKWISE, MOVING_COUNTERCLOCKWISE, BLOCKED, TRAINSTATION};

    private final String identifier;
    private final int priority;
    private final int numLength; // Wagons oder Achsen oder whatever wir halt zählen können

    private TrainState state;

    public Train(String identifier, int priority, int numLength) {
        this.identifier = identifier;
        this.priority = priority;
        this.numLength = numLength;
    }

    public boolean block() {
        return switchState(TrainState.BLOCKED);
    }

    private boolean switchState(TrainState newState) {
        if(this.state == newState) return false;

        // logik checks für spezielle Übergänge (z.B. sollte Clockwise -> Counterclockwise nicht vorkommen)

        this.state = newState;
        return true;
    }
}
