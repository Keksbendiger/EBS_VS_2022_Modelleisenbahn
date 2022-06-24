/**
			SerialMQTT
		by Adrian Petzold
   
   Available Protocols: 'mqtt'
   usage: open <protocol> <ip> <port>
*/

#include <ESP8266WiFi.h>
#include <MQTT.h>

#define START_OF_TEXT '\u0002'
#define START_OF_TEXT_DBG '<'
#define END_OF_TEXT '\u0003'
#define END_OF_TEXT_DBG '>'

#define BUFFER_SIZE 8192

const char* SSID = "WLAN NETZWERKNAME";
const char* PSK = "PASSWORT FÜR WLAN";

const char* TOPIC = "/home/data";

String clientId = "serialmqtt_" + String(random(0xffff), HEX);

WiFiClient net;
MQTTClient client;

void receiveSerial(String serialIn)
{
  // Might be start of payload (START_OF_TEXT) or might be a command like 'open'
  if (serialIn.substring(0, 4) == "open")
  {
    // It's the open command, check for available protocols
    if (serialIn.indexOf("mqtt") > -1)
    {
      // Protocol MQTT has been chosen.
      // parse IP Address and Port to connect to MQTT Broker.

      int mqttIndex = serialIn.indexOf("mqtt");

      // + 1 for leading space and + 4 cause mqtt consists of 4 characters.
      int ipStartIndex = mqttIndex + 1 + 4;
      String ip = serialIn.substring(ipStartIndex, serialIn.indexOf(" ", ipStartIndex));

      // + 1 for space and ip.length for previous argument
      String port = serialIn.substring(ipStartIndex + 1 + ip.length());

      // Set IP and Port of MQTT Broker
      client.begin(ip.c_str(), port.toInt(), net);
      connectMQTT();
      //client.setHost(ip.c_str(), port.toInt());
      Serial.print("Successfully set Broker IP to '");
      Serial.print(ip.c_str());
      Serial.println(":" + port + "'");
    }
  } else
  {
    // Check if data being sent is for SerialMQTT
    if (serialIn.substring(0, 1)[0] == START_OF_TEXT_DBG)
    {
      // Payload for MQTT
      //String payload = serialIn.substring(1, serialIn.indexof(END_OF_TEXT_DBG));
      String payload = serialIn.substring(1, serialIn.length() - 1);

      if (WiFi.status() != WL_CONNECTED)
      {
        connectWifi();
      }
      if (!client.connected()) {
        connectMQTT();
      }

      client.publish(TOPIC, payload.c_str());
      Serial.println("published!");
    }
  }
}

void connectWifi()
{
  Serial.print("connecting to WIFI..");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(1000);
  }
  Serial.println("\nSuccessfully connected to WIFI!");
}

void connectMQTT()
{
  Serial.print("\nconnecting to MQTT Broker as " + clientId);
  while (!client.connect(clientId.c_str(), "public", "public")) {
    Serial.print(".");
    delay(1000);
  }
  Serial.println("\nconnected!");
}

void setup() {
  Serial.begin(115200);
  WiFi.begin(SSID, PSK);
  connectWifi();
}

void loop()
{
  client.loop();
  delay(10);  // <- fixes some issues with WiFi stability

  if (Serial.available() > 0)
  {
    String serialIn = Serial.readString();
    receiveSerial(serialIn);
  }
}
