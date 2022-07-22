#include "EspMQTTClient.h"

#define D10 1
#define RX 3
#define D8 15
#define D7 13
#define D6 12
#define D5 14
#define D4 2
#define D3 0
#define D2 4
#define D1 5
#define D0 16
#define analogInPin A0


EspMQTTClient client(
  "ChuuuChuuu",       // AP SSID
  "Verspaetung",    // AP Password
  "192.168.0.10",   // MQTT Broker server ip
  "STATION_THREE",      // Client name that uniquely identify your device
  1883
);

int count_one = 0, count_two, count_three, count_four, count_five, count_six = 0;
int noSightCount_one = 0, noSightCount_two, noSightCount_three, noSightCount_four, noSightCount_five, noSightCount_six = 0;
int lastEdge_one = 0, lastEdge_two, lastEdge_three, lastEdge_four, lastEdge_five, lastEdge_six = 0;
/*int count = 0;
  int noSightCount = 0;
  int lastEdge = 0;
*/
int DELAY_MS = 5;
int NO_SIGHT_THRESHHOLD = 500 / DELAY_MS;

void setup() {
  Serial.begin(115200);  
  //client.enableDebuggingMessages(); // Enable debugging messages sent to serial
}

void onConnectionEstablished() {
  //Serial.print("MQTT Verbunden!");

  client.subscribe("actuator/3", [](const String & payload) {
    actuate(D0, D1, payload);
  });

    client.subscribe("cmd/start", [](const String & payload) {
      if(payload.equals("*")){
        Serial.print("<1>");
      } else {
        //ICE 1,-ICE_ALT 2, BR110 3, BR215 4, BR142 5
        Serial.print("<t 1 "); 
        Serial.print(payload);
        Serial.print(" 70 1>");
      }
  });

    client.subscribe("cmd/stop", [](const String & payload) {
      if(payload.equals("*")){
        Serial.print("<0>");
      } else {
        Serial.print("<t 1 "); //ICE 1,-ICE_ALT 2, BR110 3, BR215 4, BR142 5
        Serial.print(payload);
        Serial.print(" 0 1>");
      }
  });
}

// Prellzeit beachten & 11.65mA für LEDs beachten
void loop() {

  handleSensor(D2, count_one, noSightCount_one, lastEdge_one, "sensor/1");
  handleSensor(D5, count_two, noSightCount_two, lastEdge_two, "sensor/2");
  handleSensor(D6, count_three, noSightCount_three, lastEdge_three, "sensor/3");
  client.loop();
  delay(DELAY_MS);
}

void handleSensor(int pin, int &count, int &noSightCount, int &lastEdge, const String& topic) {
  int reading = analogRead(pin);

  //Serial.println(reading);
  //delay(1000);
  if (reading < 300) {
    // SIGHT, POSSIBLE END OR CLEAR
    if (count != 0 && ++noSightCount == NO_SIGHT_THRESHHOLD) {
      noSightCount = 0;

      //Serial.print(topic + " Zählungsende: ");
      //Serial.println(count);
      
      if(count % 2 != 0) {
        count--;
      }

       // Send final data over MQTT
      char countBuffer[2];
      itoa(count, countBuffer, 10);
      client.publish(topic, countBuffer);

      
      count = 0;

    }
    lastEdge = 0;
  } else {
    // POSSIBLE BEGIN OR END

    if (lastEdge == 0) {
      //Serial.print(topic + " Zug im Weg: ");
      //Serial.println(reading);
      count++;
      noSightCount = 0;
    }

    lastEdge = 1;
  }
}

void actuate(int rightPin, int leftPin, const String& payload) {
  if (payload == "R") {
    digitalWrite(rightPin, HIGH);
    delay(100);
    analogWrite(rightPin, LOW);
  } else if (payload == "L") {
    digitalWrite(leftPin, HIGH);
    delay(100);
    analogWrite(leftPin, LOW);
  }
}
