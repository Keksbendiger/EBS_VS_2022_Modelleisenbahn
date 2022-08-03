package core;

import mqtt.MqttClient;
import util.Logger;

import java.util.HashMap;
import java.util.Map;

public class Train {

    public enum TrainDirection {CLOCKWISE, COUNTERCLOCKWISE}

    public static Map<String, Train> trains = new HashMap<>();

    public static Train get(String identifier) {
        Train train = trains.get(identifier);
        if(train == null) Logger.err("Train with name " + identifier + " not found.");
        return train;
    }

    private final String identifier;
    private final int trainId;
    private final int priority;
    private final int delayMultiplier;
    private final int numLength; // Wagons oder Achsen oder whatever wir halt zählen können

    private TrainDirection direction;
    private ETrainState state;

    public Train(String identifier, int trainId, int priority, int numLength, TrainDirection direction, int delayMultiplier) {
        this.identifier = identifier;
        this.trainId = trainId;
        this.priority = priority;
        this.numLength = numLength;
        this.direction = direction;
        this.delayMultiplier = delayMultiplier;
        this.state = ETrainState.TRAINSTATION;

        trains.put(identifier, this);
    }

    public boolean block() {
        return switchState(ETrainState.BLOCKED);
    }

    public boolean unblock() {
        return switchState(ETrainState.MOVING);
    }

    private boolean switchState(ETrainState newState) {
        if (this.state == newState) return false;

        // logik checks für spezielle Übergänge (z.B. sollte Clockwise -> Counterclockwise nicht vorkommen)

        switch (newState) {
            case BLOCKED:   //fall through
            case TRAINSTATION:
                MqttClient.getInstance().sendTrainStop(String.valueOf(trainId));
                break;
            case MOVING:
                MqttClient.getInstance().sendTrainStart(String.valueOf(trainId));
                break;
        }

        this.state = newState;
        Logger.log("Train " + identifier + " changed state to " + newState);
        return true;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getPriority() {
        return priority;
    }

    public int getDelayMultiplier() {
        return delayMultiplier;
    }

    public int getNumLength() {
        return numLength;
    }

    public ETrainState getState() {
        return state;
    }

    public int getTrainId() {
        return trainId;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
