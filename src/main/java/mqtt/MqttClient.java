package mqtt;

import core.Controller;
import core.ETrackSwitch;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import util.Logger;

import java.nio.charset.StandardCharsets;

public class MqttClient
{

    //region Definitions
    private static final String SWITCH_TOPIC_PREFIX = "actuator/";
    private static final byte[] SWITCH_DIRECTION_LEFT = "L".getBytes();
    private static final byte[] SWITCH_DIRECTION_RIGHT = "R".getBytes();

    //endregion Definitions

    // Client options
    private static final String BROKER_HOST = "tcp://192.168.0.10:1883";
    private static final String CLIENT_ID = "master";

    // Railway Topics for start and stopping individual Trains
    private static final String START_TOPIC = "cmd/start";
    private static final String STOP_TOPIC = "cmd/stop";

    // Axle Counter Topics for polling data
//    private static final String AXLE_COUNTER_1_TOPIC = "sensor/1";
//    private static final String AXLE_COUNTER_2_TOPIC = "sensor/2";
//    private static final String AXLE_COUNTER_3_TOPIC = "sensor/3";
//    private static final String AXLE_COUNTER_4_TOPIC = "sensor/4";
//    private static final String AXLE_COUNTER_5_TOPIC = "sensor/5";
//    private static final String AXLE_COUNTER_6_TOPIC = "sensor/6";
//    private static final String AXLE_COUNTER_7_TOPIC = "sensor/7";
//    private static final String AXLE_COUNTER_8_TOPIC = "sensor/8";
//    private static final String AXLE_COUNTER_9_TOPIC = "sensor/9";
//    private static final String AXLE_COUNTER_10_TOPIC = "sensor/10";
//    private static final String AXLE_COUNTER_11_TOPIC = "sensor/11";
//    private static final String AXLE_COUNTER_12_TOPIC = "sensor/12";
//    private static final String AXLE_COUNTER_13_TOPIC = "sensor/13";
//    private static final String AXLE_COUNTER_14_TOPIC = "sensor/14";
//    private static final String AXLE_COUNTER_15_TOPIC = "sensor/15";
//    private static final String AXLE_COUNTER_16_TOPIC = "sensor/16";

    // Railway Switch Topics
    private static final String RAIL_SWITCH_1_TOPIC = "actuator/1";
    private static final String RAIL_SWITCH_2_TOPIC = "actuator/2";
    private static final String RAIL_SWITCH_3_TOPIC = "actuator/3";
    private static final String RAIL_SWITCH_4_TOPIC = "actuator/4";
    private static final String RAIL_SWITCH_5_TOPIC = "actuator/5";
    private static final String RAIL_SWITCH_6_TOPIC = "actuator/6";

    private static MqttClient instance;

    private MqttAsyncClient client;

    private MqttClient()
    {
        this.setupClient();
    }

    public static MqttClient getInstance()
    {
        if(instance == null)
            instance = new MqttClient();
        return instance;
    }

    private void setupClient()
    {
        try
        {
            client = new MqttAsyncClient(BROKER_HOST, CLIENT_ID, new MemoryPersistence());

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    try {
                        client.connect();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    int sensorId = Integer.parseInt(topic.split("/")[1]);
                    String msg = new String(mqttMessage.getPayload());
                    Logger.mqtt("Sensor #" + sensorId + ": " + msg);

                    int msgParsed = Integer.parseInt(msg);

                    Controller.get().receivedCount(sensorId, msgParsed);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                }
            });

            // Block Thread until connection has been successfully established
            client.connect().waitForCompletion();

            for (int i = 1; i <= 16; i++) {
                String topic = "sensor/" + i;
                client.subscribe(topic, 2);
                Logger.mqtt("MQTT Client subscribing to [" + topic + "]");
            }
        }
        catch(MqttException e)
        {
            e.printStackTrace();
        }
    }

    private void publish(String topic, String payload) {
        publish(topic, payload.getBytes());
    }

    private void publish(String topic, byte[] payload)
    {
        try
        {
            // TODO permanent QoS or depending on what command is being sent?
            client.publish(topic, payload, 2, false);
            Logger.mqtt("Published " + new String(payload) + " on " + topic);
        }
        catch(MqttException e)
        {
            Logger.err("Error while publishing on topic '" + topic + "' with payload:\n" + new String(payload));
            e.printStackTrace();
        }
    }

    // TODO Maybe move functions into abstract class or interface, there is no real need for them all to be in this file.
    public void sendTrainStart(String identifier)
    {
        publish(START_TOPIC, identifier);
    }

    public void sendTrainStop(String identifier)
    {
        publish(STOP_TOPIC, identifier);
    }

    public void emergencyStopAllTrains() {
        sendTrainStop("*");
    }

    public void sendSwitchActuator(int id, boolean toLeft) {
        publish(SWITCH_TOPIC_PREFIX + id, toLeft ? SWITCH_DIRECTION_LEFT : SWITCH_DIRECTION_RIGHT);
    }

    public void sendSwitchActuator(ETrackSwitch eTrackSwitch, boolean toLeft) {
        sendSwitchActuator(eTrackSwitch.getId(), toLeft);
    }

    public void initializeBus() {
        sendTrainStart("*");
    }
}
