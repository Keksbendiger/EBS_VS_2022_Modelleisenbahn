package gui;

import mqtt.MqttClient;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

//--------------------------------------------------//
// author:   Keksbendiger <keksbendiger@gmail.com>
// project:  EBS_VS_2022_Modelleisenbahn
// created:  03.08.2022
//--------------------------------------------------//
public class BusButton {
    private static Map<String, BusButton> busButtons = new HashMap<>();

    public static void switchButtonState(String id, boolean switchOn) {
        for (Map.Entry<String, BusButton> entry : busButtons.entrySet()) {
            if(entry.getKey().equals(id)) {
                entry.getValue().switchState(switchOn);
                return;
            }
        }
    }

    private String name;
    private String busId;
    private JButton btn;


//region Construct

    public BusButton(String name, String busId, JButton btn) {
        this.name = name;
        this.busId = busId;
        this.btn = btn;
        btn.setText(name + " anschalten");
        btn.addActionListener(actionEvent -> {
            sendCommand(btn.getText().equals(name + " anschalten"));
        });

        busButtons.put(busId, this);
    }

//endregion
//region Getter & Setter

    //endregion
//region other methods
    public void switchState(boolean switchOn) {
        if(switchOn) {
            btn.setText(name + " ausschalten");
        } else {
            btn.setText(name + " anschalten");
        }
    }

    private void sendCommand(boolean start) {
        if(start) {
            MqttClient.getInstance().sendTrainStart(busId);
        } else {
            MqttClient.getInstance().sendTrainStop(busId);
        }
    }
//endregion
}
