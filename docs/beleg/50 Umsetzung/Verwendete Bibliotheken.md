## Verwendete Bibliotheken <!--AP-->
### DCC++ BaseStation

(https://github.com/DccPlusPlus/BaseStation)

### EspMQTTClient

(https://github.com/plapointe6/EspMQTTClient)
Diese Bibliothek kann über die Arduino IDE einfach ausgewählt und zum Projekt hinzugefügt werden.

### Paho MQTTv3 Library 

Die Protokollversion 5 von MQTT wird vom eingesetzten Broker (mosquitto) nicht unterstützt, daher wird auf allen beteiligten Systemen die Version 3 beziehungsweise 3.1 eingesetzt.
Zur Verwendung müssen in Maven (pom.xml) folgende Ergänzungen vorgenommen werden:

```
    <repository>
        <id>Eclipse Paho Repo</id>
        <url>https://repo.eclipse.org/content/repositories/paho-releases/</url>
    </repository>

    <dependency>
        <groupId>org.eclipse.paho</groupId>
        <artifactId>org.eclipse.paho.client.mqttv3</artifactId>
        <version>1.2.5</version>
    </dependency>
```

### Arduino AVR

(https://github.com/arduino/ArduinoCore-avr)

### Tabelle mit Referenzen

| Library             | URL                                         | Projekt                      | Programmiersprache |
|---------------------|---------------------------------------------|------------------------------|--------------------|
| DCC++ BaseStation   | https://github.com/DccPlusPlus/BaseStation  | Arduino mit Motorshield      | C/++               |
| EspMQTTClient       | https://github.com/plapointe6/EspMQTTClient | ESP8266                      | C/++               |
| Arduino AVR         | https://github.com/arduino/ArduinoCore-avr  | Arduino, ESP8266             | C/++               |
| Paho MQTTv3 Library | https://github.com/eclipse/paho.mqtt.java   | Leitstation auf Raspberry PI | Java               |
