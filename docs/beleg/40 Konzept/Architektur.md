## Master-Slave-Prinzip
## Slaves im Echtzeitbetrieb
### ESP8266 mit RTOS <!--LE-->
### Master ohne EBS
## Komponenten
* MQTT Broker
* Achszähler (MQTT Client)
* Weichensteller (MQTT Client)
* Züge (Bus Client)
* Gleisbuscontroller (Bus Master)
* Leitstelle (MQTT Client)
* GUI
### Gleis-Bussystem
### Achszähler
### Weichenschaltung
### MQTT-Broker als zentrales Datenübertragungsmedium
### Leitstelle <!--CH-->
Die Leitstelle bezeichnet das zentrale Programm, welches in Abhängigkeit der eingehenden Daten Züge startet und stoppt, Weichen stellt und gegebenenfalls Fehler beziehungsweise Ausnahmezustände erkennt und darauf reagiert. Der Rechner, auf dem jenes Programm ausgeführt wird, kann ebenfalls als Leitstelle bezeichnet werden.

#### Initialisierung des Netzes
Implementiert werden soll ein Framework, welches nicht an den konkreten Modellaufbau gebunden ist. Das Gleisnetz muss also konfigurierbar sein.
Erst beim Starten der Anwendung soll dementsprechend das spezifische Gleisnetz im System registriert werden.

Dies geschieht in vier Phasen:
1. Initialisierung der vorerst losen Gleissektionen (Streckenabschnitte)
2. Initialisierung der Weichen
3. Initialisierung der Achszähler
4. Initialisierung der Züge

Bei der Registration einer Weiche werden jeweils die Gleissektionen am Eingang, sowie am linken und rechten Ausgang definiert.
Hat der Administrator die Konfiguration korrekt durchgeführt, sind anschließend alle Gleissektionen entsprechend dem Modell miteinander verbunden.

Da sowohl Weichen mit drei Achszählern als auch Weichen mit nur zwei Achszählern vorgesehen sind und sich daraus starke Einflüsse auf das Verhalten der entsprechenden Zustandsautomaten ergeben, werden diese inklusive ihrer Zustandsüberführungen gesondert initialisiert.

Zuletzt werden die Startzustände der Züge erfasst. Dazu zählen Bezeichnung, Bus-ID, Achszahl, Fahrtrichtung und die initiale Gleissektion.

#### Initialisierung des MQTT-Clients
Der MQTT-Client wird konfiguriert und verbindet sich mit dem Broker. Für jeden Sensor ist ein Topic ```sensor/<SensorId>``` vorgesehen - diese werden vom Client abonniert.
Ferner wird ein Callback hinterlegt, welcher die eingehenden Daten an den Zustandsautomaten weiterreicht.
Anschließend wird der Befehl gesendet, den Gleisbus in Betrieb zu nehmen.

#### Gleissektionsreservierungen
Programmatisch betrachtet fährt jeder Zug immer nur von Gleissektion zu Gleissektion.
Bei jedem solchen Übergang wird eine Anfrage zum Befahren der nachfolgenden Sektion generiert und geprüft.
Ist das Zielsegment belegt oder reserviert, werden gegebenenfalls existierende Ausweichsektionen geprüft.
Sind auch diese nicht verfügbar, wird der Zug gestoppt.

#### Behandlung von eingehenden Sensordaten
Aus Sicht der Leitstelle sind die Sensorstationen Black Boxes. Diese senden lediglich einen finalen Zählwert, sobald ein Zug sie vollständig durchquert hat.
Die ID des Sensors wird am Gleisnetz aufgelöst. Anhand der belegten und reservierten Gleissektionen wird ermittelt, welcher Zug die Freigabe zum Passieren besitzt.
Finden sich hier mehrere Kandidaten, muss ein Fehler aufgetreten sein, da immer nur ein Zug über diese Freigabe verfügen kann.

Der Zählwert wird mit der Achszahl des ermittelten Zuges verglichen. Wird eine Abweichung festgestellt, kann die zurückliegende Gleissektion nicht freigegeben werden.
Um Unfälle zu vermeiden und das erneute Ankoppeln eventuell verlorener oder entgleister Wagons zu ermöglichen, wird ein globaler Notstop ausgelöst.

Stimmt die Zählung hingegen mit der Erwartung überein, wird der Zustand des Gleisnetzes aktualisiert und gegebenenfalls die zurückliegende Gleissektion freigegeben.

#### Freigabe einer Gleissektion
Verlässt ein Zug mit korrekter Achszählung eine Gleissektion, wird diese für andere Züge wieder freigegeben.
Zeitgleich wird eine Überprüfung angestoßen, die alle als _wartend_ markierten Reservierungsanfragen mit der freigewordenen Sektion vergleicht.
Von allen Kandidaten wird derjenige mit der höchsten Zugpriorität zur Weiterfahrt freigegeben und gestartet.

### Grafische Ausgabe <!--CH-->
Auf einer grafischen Oberfläche sollen durch die Leitstelle folgende Informationen in Echtzeit dargestellt werden:
* Anordnung des Gleisnetzes (statisch)
* Stellung der jeweiligen Weichen
* Belegung und Reservierung der Gleissektionen inklusive Bezeichner der betreffenden Züge

Eine direkte Einflussnahme wie etwa das Umstellen einer Weiche ist hierbei nicht vorgesehen, da das System vollautomatisch arbeiten und reagieren soll.
Zu Testzwecken können Weichen jedoch über ein physisches Steuerpult manipuliert werden.