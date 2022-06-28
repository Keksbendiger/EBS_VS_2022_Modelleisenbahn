package core;

import mqtt.MqttClient;

public class Train {
    public enum TrainState {MOVING, BLOCKED, TRAINSTATION}

    ;

    public enum TrainDirection {CLOCKWISE, COUNTERCLOCKWISE}


    private final String identifier;
    private final int priority;
    private final int numLength; // Wagons oder Achsen oder whatever wir halt zählen können

    private TrainDirection direction;
    private TrainState state;

    public Train(String identifier, int priority, int numLength, TrainDirection direction) {
        this.identifier = identifier;
        this.priority = priority;
        this.numLength = numLength;
        this.direction = direction;
        this.state = TrainState.TRAINSTATION;
    }

    public boolean block() {
        return switchState(TrainState.BLOCKED);
    }

    public boolean unblock() {
        return switchState(TrainState.MOVING);
    }

    private boolean switchState(TrainState newState) {
        if (this.state == newState) return false;

        // logik checks für spezielle Übergänge (z.B. sollte Clockwise -> Counterclockwise nicht vorkommen)

        switch (newState) {
            case BLOCKED:
            case TRAINSTATION:
                MqttClient.getInstance().sendTrainStop(identifier);
                break;
            case MOVING:
                MqttClient.getInstance().sendTrainStart(identifier);
                break;
        }

        this.state = newState;
        return true;
    }
}
