package mqtt;

import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;

import java.nio.charset.StandardCharsets;

public class MqttClient
{
    // Client options
    private static final String BROKER_HOST = "tcp://127.0.0.1:1883";
    private static final String CLIENT_ID = "master";

    // Railway Topics for start and stopping individual Trains
    private static final String START_TOPIC = "cmd/start";
    private static final String STOP_TOPIC = "cmd/stop";

    // Axle Counter Topics for polling data
    private static final String AXLE_COUNTER_1_TOPIC = "sensor/1";
    private static final String AXLE_COUNTER_2_TOPIC = "sensor/2";
    private static final String AXLE_COUNTER_3_TOPIC = "sensor/3";
    private static final String AXLE_COUNTER_4_TOPIC = "sensor/4";
    private static final String AXLE_COUNTER_5_TOPIC = "sensor/5";
    private static final String AXLE_COUNTER_6_TOPIC = "sensor/6";
    private static final String AXLE_COUNTER_7_TOPIC = "sensor/7";
    private static final String AXLE_COUNTER_8_TOPIC = "sensor/8";
    private static final String AXLE_COUNTER_9_TOPIC = "sensor/9";
    private static final String AXLE_COUNTER_10_TOPIC = "sensor/10";
    private static final String AXLE_COUNTER_11_TOPIC = "sensor/11";
    private static final String AXLE_COUNTER_12_TOPIC = "sensor/12";
    private static final String AXLE_COUNTER_13_TOPIC = "sensor/13";
    private static final String AXLE_COUNTER_14_TOPIC = "sensor/14";
    private static final String AXLE_COUNTER_15_TOPIC = "sensor/15";
    private static final String AXLE_COUNTER_16_TOPIC = "sensor/16";

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
