#include "EspMQTTClient.h"

#define D10 1
#define D9  3
#define D8 15
#define D7 13
#define D6 12
#define D5 14
#define D4 2
#define D3 0
#define D2 4
#define D1 5
#define D0 16

EspMQTTClient client(
        "ChooChoo",       // AP SSID
        "Verspaetung",    // AP Password
        "192.168.0.10",   // MQTT Broker server ip
        "ESP8266",      // Client name that uniquely identify your device
        1883
);

int reading;

int count = 0;
int noSightCount = 0;
int lastEdge = 0;

int DELAY_MS = 5;
int NO_SIGHT_THRESHHOLD = 2000 / DELAY_MS;

void setup() {
    Serial.begin(115200);
    tone(D8, 56000); // TODO actual frequency slightly higher than 56k
}

// Prellzeit beachten & 11.65mA für LEDs beachten
void loop() {
    reading = analogRead(D6);

    if (reading < 10) {
        // SIGHT, POSSIBLE END OR CLEAR
        if (count != 0 && ++noSightCount == NO_SIGHT_THRESHHOLD) {
            noSightCount = 0;

            Serial.print("Zählungsende: ");

            // Zufällige Zählungen treten auch ohne Vorbeifahrt des Zuges auf. Dies kann natürlich auch währenddessen auftreten.
            // Da die totale Achsenanzahl immer gerade ist wird hier einfach -1 gerechnet falls die Zahl ungerade ist.
            if(count % 2 == 1)
                count--;

            Serial.println(count);
            count = 0;

        }
        lastEdge = 0;
    } else {
        // POSSIBLE BEGIN OR END

        if (lastEdge == 0) {
            Serial.print("Zug im Weg: ");
            Serial.println(reading);
            count++;
            noSightCount = 0;

            // akustisches Signal durch Beeper um Achsenzählungen einfacher zu machen und die Hardware darauf abzustimmen.
            tone(D7, 550, 50);
        }

        lastEdge = 1;
    }

    client.loop();
    delay(DELAY_MS);
}