# DCC++ BaseStation

(https://github.com/DccPlusPlus/BaseStation)

# EspMQTTClient

(https://github.com/plapointe6/EspMQTTClient)

# Paho MQTTv3 Library 

Verwendung Version 3 da MQTT-Broker nur Protokollversion 3.1 und drunter unterstützt

Maven (pom.xml)

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

# Arduino AVR

(https://github.com/arduino/ArduinoCore-avr)

# Tabelle mit Referenzen

| Library             | URL                                         | Projekt                      | Programmiersprache |
|---------------------|---------------------------------------------|------------------------------|--------------------|
| DCC++ BaseStation   | https://github.com/DccPlusPlus/BaseStation  | Arduino mit Motorshield      | C/++               |
| EspMQTTClient       | https://github.com/plapointe6/EspMQTTClient | ESP8266                      | C/++               |
| Arduino AVR         | https://github.com/arduino/ArduinoCore-avr  | Arduino, ESP8266             | C/++               |
| Paho MQTTv3 Library | https://github.com/eclipse/paho.mqtt.java   | Leitstation auf Raspberry PI | Java               |
