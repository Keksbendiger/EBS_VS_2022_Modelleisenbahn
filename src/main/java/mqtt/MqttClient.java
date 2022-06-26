package mqtt;

import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;

import java.nio.charset.StandardCharsets;

public class MqttClient
{
    // Client options
    private static final String BROKER_HOST = "tcp://127.0.0.1:1883";
    private static final String CLIENT_ID = "CLIENT-MASTER";

    // Railway Topics for start and stopping individual Trains
    private static final String START_TOPIC = "START_TRAIN";
    private static final String STOP_TOPIC = "STOP_TRAIN";

    // Axle Counter Topics for polling data
    private static final String AXLE_COUNTER_1_TOPIC = "A_1";
    private static final String AXLE_COUNTER_2_TOPIC = "A_2";
    private static final String AXLE_COUNTER_3_TOPIC = "A_3";
    private static final String AXLE_COUNTER_4_TOPIC = "A_4";
    private static final String AXLE_COUNTER_5_TOPIC = "A_5";
    private static final String AXLE_COUNTER_6_TOPIC = "A_6";
    private static final String AXLE_COUNTER_7_TOPIC = "A_7";
    private static final String AXLE_COUNTER_8_TOPIC = "A_8";
    private static final String AXLE_COUNTER_9_TOPIC = "A_9";
    private static final String AXLE_COUNTER_10_TOPIC = "A_10";
    private static final String AXLE_COUNTER_11_TOPIC = "A_11";
    private static final String AXLE_COUNTER_12_TOPIC = "A_12";
    private static final String AXLE_COUNTER_13_TOPIC = "A_13";
    private static final String AXLE_COUNTER_14_TOPIC = "A_14";
    private static final String AXLE_COUNTER_15_TOPIC = "A_15";
    private static final String AXLE_COUNTER_16_TOPIC = "A_16";

    // Railway Switch Topics
    private static final String RAIL_SWITCH_1_TOPIC = "S_1";
    private static final String RAIL_SWITCH_2_TOPIC = "S_2";
    private static final String RAIL_SWITCH_3_TOPIC = "S_3";
    private static final String RAIL_SWITCH_4_TOPIC = "S_4";
    private static final String RAIL_SWITCH_5_TOPIC = "S_5";
    private static final String RAIL_SWITCH_6_TOPIC = "S_6";

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

            // Block Thread until connection has been successfully established
            client.connect().waitForCompletion();
        }
        catch(MqttException e)
        {
            e.printStackTrace();
        }
    }

    private void publish(String topic, byte[] payload)
    {
        try
        {
            // TODO permanent QoS or depending on what command is being sent?
            client.publish(topic, payload, 2, false);
        }
        catch(MqttException e)
        {
            System.err.println("Error while publishing on topic '" + topic + "' with payload:\n" + new String(payload));
            e.printStackTrace();
        }
    }

    // TODO Maybe move functions into abstract class or interface, there is no real need for them all to be in this file.
    public void sendTrainStart(String identifier)
    {
        publish(START_TOPIC, identifier.getBytes(StandardCharsets.UTF_8));
    }

    public void sendTrainStop(String identifier)
    {
        publish(STOP_TOPIC, identifier.getBytes(StandardCharsets.UTF_8));
    }
}
