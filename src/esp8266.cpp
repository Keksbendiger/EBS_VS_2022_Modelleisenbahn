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
  "STATION_ONE",      // Client name that uniquely identify your device
  1883
);

int count_one = 0, count_two, count_three, count_four, count_five, count_six = 0;
int noSightCount_one = 0, noSightCount_two, noSightCount_three, noSightCount_four, noSightCount_five, noSightCount_six = 0;
int lastEdge_one = 0, lastEdge_two, lastEdge_three, lastEdge_four, lastEdge_five, lastEdge_six = 0;

//int reading;

/*int count = 0;
  int noSightCount = 0;
  int lastEdge = 0;
*/
int DELAY_MS = 5;
int NO_SIGHT_THRESHHOLD = 2000 / DELAY_MS;

void setup() {
  Serial.begin(115200);

  pinMode(D7, INPUT);
  
  client.enableDebuggingMessages(); // Enable debugging messages sent to serial
}

void onConnectionEstablished() {
  Serial.print("MQTT Verbunden!");

  client.subscribe("actuator/1", [] (const String & payload)  {
    actuate(D2, D3, payload);
  });

  client.subscribe("actuator/2", [](const String & payload) {
    actuate(D0, D1, payload);
  });
}

// Prellzeit beachten & 11.65mA für LEDs beachten
void loop() {

  handleSensor(D5, count_one, noSightCount_one, lastEdge_one, "SENSOR 1");
  handleSensor(D6, count_two, noSightCount_two, lastEdge_two, "SENSOR 2");
  handleSensor(D4, count_three, noSightCount_three, lastEdge_three, "SENSOR 3");
  //handleSensor(RX, count_four, noSightCount_four, lastEdge_four, "SENSOR 4");
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

      Serial.print(topic + " Zählungsende: ");
      Serial.println(count);
      count = 0;

    }
    lastEdge = 0;
  } else {
    // POSSIBLE BEGIN OR END

    if (lastEdge == 0) {
      Serial.print(topic + " Zug im Weg: ");
      Serial.println(reading);
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
