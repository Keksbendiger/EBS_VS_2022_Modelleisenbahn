package core;

import gui.GuiHandler;
import mqtt.MqttClient;

import java.util.HashMap;
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
    public static TrackSwitch get(ETrackSection from, ETrackSection to) {
        for (Map.Entry<ETrackSwitch, TrackSwitch> entry : switches.entrySet()) {
            if(entry.getValue().ingoing.getIdentifier() == from) {
                if(entry.getValue().outgoing_L.getIdentifier() == to || entry.getValue().outgoing_R.getIdentifier() == to) {
                    return entry.getValue();
                }
            } else if(entry.getValue().ingoing.getIdentifier() == to) {
                if(entry.getValue().outgoing_L.getIdentifier() == from || entry.getValue().outgoing_R.getIdentifier() == from) {
                    return entry.getValue();
                }
            }
        }
        System.err.println("No TrackSwitch found: from " + from.toString() + " to " + to.toString());
        return null;
    }

    private ETrackSwitch identifier;
    private TrackSection ingoing;
    private TrackSection outgoing_L;        // used with directive 'L'
    private TrackSection outgoing_R;        // used with directive 'R'

    private boolean directionIsL;
    private boolean isTraversing = false;   // TODO

//region Construct

    public TrackSwitch(ETrackSwitch identifier, TrackSection ingoing, TrackSection outgoing_L, TrackSection outgoing_R) {
        this.identifier = identifier;
        this.ingoing = ingoing;
        this.outgoing_L = outgoing_L;
        this.outgoing_R = outgoing_R;

        inverseDirection();

        switches.put(identifier, this);
    }
//endregion

    private void inverseDirection() {
        directionIsL = !directionIsL;
        MqttClient.getInstance().sendSwitchActuator(identifier, directionIsL);
        GuiHandler.getInstance().setSwitchDirection(identifier, directionIsL ? outgoing_L.getIdentifier() : outgoing_R.getIdentifier());
    }

    public void switchToSection(TrackSection sectionTo, TrackSection sectionFrom) {
        // TODO bei gleis B beide Weichen zusammen schalten
        if(isTraversing) return;
        if(sectionTo == outgoing_L || sectionFrom == outgoing_L) {
            if(!directionIsL) {
                inverseDirection();
            }
        } else if(sectionFrom == outgoing_R || sectionTo == outgoing_R) {
            if(directionIsL) {
                inverseDirection();
            }
        }
        // TODO throw exception
    }

    public ETrackSwitch getIdentifier() {
        return identifier;
    }

//region other methods

//endregion
}
