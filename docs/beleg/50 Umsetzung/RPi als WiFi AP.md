## Raspberry Pi als WiFi AP
Der MQTT-Broker soll auf dem Raspberry Pi betrieben werden.
Um die Kommunikation zu Clients zu ermöglichen, müssen sich diese im selben Netzwerk befinden.
Ethernet scheidet aus, da ESP8266 nicht über einen entsprechenden Anschluss verfügt.
Da kein frei zugängliches WLAN-Netzwerk zur Verfügung steht, wird der Raspberry Pi als Access Point betrieben.
Damit geht leider die Möglichkeit verloren, des Raspberry Pi selbst über WLAN mit dem Internet zu verbinden.