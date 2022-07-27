## Erweiterungen
Mit der Umsetzung der drei folgenden Komponenten wird die Nutzung der Software durch Außenstehende möglich.
Alle Anpassungen an den konkreten Modellaufbau erfolgen dann über zur Laufzeit eingelesene Konfigurationsdateien.

### Automatisch generiertes GUI <!--CH-->
Aktuell ist die grafische Oberfläche hartkodiert. Da jedoch das Gleisnetz selbst bereits dynamisch Konfiguriert werden kann,
sollte auch eine automatische Generierung der Oberfläche möglich sein.
Vereinfachend könnte hier gefordert werden, dass nur gerade Segmente oder Kurven als Vielfache von 90° zulässig sind.
Als Grundlage könnten Algorithmen dienen, die normalerweise zum Visualisieren von Graphen eingesetzt werden.
Denn die Gleissegmente können als Kanten und die Segmentübergänge als Knoten interpretiert werden.

### Konfigurationsdateien für Züge <!--CH-->
Jeder Zug verfügt über eindeutige Parameter, die dem System bekannt sein müssen. Dies umfasst beispielsweise die Bus-ID der Lok oder die Anzahl der Achsen.
Hier bietet es sich an, pro Zug eine Konfigurationsdatei im JSON-Format zu hinterlegen, welche beim Programmstart automatisch geladen wird.
Änderungen an den Zügen würden somit ohne Build-Vorgang des Programms möglich.

### Konfigurationsdatei für Gleisnetz <!--CH-->
Analog zu den Zügen könnte natürlich auch das Gleisnetz dynamisch aus einer Datei geladen werden.
Die Zuordnung der Sensorstationen / IDs müsste in dem Fall an gleicher Stelle erfolgen.
