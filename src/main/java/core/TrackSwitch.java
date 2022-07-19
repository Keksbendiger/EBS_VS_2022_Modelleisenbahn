package core;

import gui.GuiHandler;
import mqtt.MqttClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  EBS_VS_2022_Modelleisenbahn
// created:  11.07.2022
//--------------------------------------------------//
public class TrackSwitch {
    public static Map<ETrackSwitch, TrackSwitch> switches = new HashMap<>();

    public static TrackSwitch get(ETrackSwitch identifier) {
        return switches.get(identifier);
    }

    private ETrackSwitch identifier;
    private TrackSection ingoing;
    private TrackSection outgoing_L;        // used with directive 'L'
    private TrackSection outgoing_R;        // used with directive 'R'

    private boolean directionIsL;
    private boolean isTraversing = false;

//region Construct

    public TrackSwitch(ETrackSwitch identifier, TrackSection ingoing, TrackSection outgoing_L, TrackSection outgoing_R) {
        this.identifier = identifier;
        this.ingoing = ingoing;
        this.outgoing_L = outgoing_L;
        this.outgoing_R = outgoing_R;

        switches.put(identifier, this);
    }
//endregion

    private void inverseDirection() {
        directionIsL = !directionIsL;
        MqttClient.getInstance().sendSwitchActuator(identifier, directionIsL);
        GuiHandler.getInstance().setSwitchDirection(identifier, directionIsL ? outgoing_L.getIdentifier() : outgoing_R.getIdentifier());
    }

    public boolean switchToSection(TrackSection section) {
        if(isTraversing) return false;
        if(section == outgoing_L) {
            if(!directionIsL) {
                inverseDirection();
            }
            return true;
        } else if(section == outgoing_R) {
            if(directionIsL) {
                inverseDirection();
            }
            return true;
        }
        // TODO throw exception
        return false;
    }

    public ETrackSwitch getIdentifier() {
        return identifier;
    }

//region other methods

//endregion
}
