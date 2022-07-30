### Aufbau Modelleisenbahn <!--NJ-->
Als der Gleisplan stand, ging es darum, die Schienen zu verlegen und die Weichen zu verkabeln. Jede Weiche besitzt 
drei IR-Receiver und drei IR-Dioden sowie die notwendigen Kabel und Widerstände. 

#### Befestigung der Dioden und Receiver an den Gleisen
Die uns zur Verfügung stehenden Gleise bestehen aus dem Gleis selbst und einer Gleisbettung aus Gummi.
Durch die dünnen anschlüsse ("Beine") der Bauteile war die Grundidee, die Dioden und Receiver durch das Gummi am Rand der 
Gleise zu stoßen und zu befestigen. Nach dem Durchstoßen des Gummis haben wir die Beinchen umgebogen und mit den 
Anschlusskablen verlötet. Wichtig hierbei war es zu beachten, dass die Kabel zu den IR-Dioden immer parallel verschaltet
werden müssen, sowie dass vor jede LED ein Widerstand angebracht werden musste.eq

### Einstellung der Lichtschranken / Achszähler <!--AP-->
Zum Inbetriebnahme der Strecke und Software war es notwendig die Sensorik aufeinander einzustellen.
Hierbei wurden unzählige Male die LEDs gebogen und verschoben um ein perfektes Sichtverhältnis von IR Diode und
IR Receiver zu erzielen.

### Testen der Achszähler <!--AP-->
Beim Testen der Achszähler ist uns aufgefallen, dass einige Züge bei den Lichtschranken entgleist wurden. 
Insbesondere der ICE mit seiner "Plastik-Schutzkappe" hatte uns in den Wahnsinn getrieben. Diese ist beim Passieren der Lichtschranke an den IR-Receiver gestoßen und wurde somit entgleist.
Mit dem Wissen konnten wir nun also unsere Strecke anpassen. Es musste darauf geachtet werden, dass die IR-Receiver nicht zu hoch aber auch nicht zu tief sind, um als Lichtschranke funktionsfähig zu werden.
Das Ausrichten der Achszähler war reinste Millimeterarbeit. Noch heute kann es sein, dass der Zug die Position einer Station verändert und somit eine Fehlmessung bewirkt. 
Der Hintergrund dafür ist hierbei das Gummi der Schienen der Roco H0 geoLine, welches das Gewicht eines zusammenhängenden Zuges nicht tragen konnte und somit so gut wie immer eine temporäre Verschiebung der Sensorik bewirkt.
Es ist davon auszugehen, dass dies der Qualität der beteiligten Bauteile geschuldet ist. Bei einer reellen Bahn würde man keine Gummimatten mit Luft unterhalb der Schiene bauen und/oder Lichtschranken über IR bauen.
Denkbar wäre es induktive Sensoren in das Schienenbett zu verankern und darüber Achszählungen zu realisieren.
Mit der Lösung aller Blockaden wurde es nun möglich, wie später auch für die Inbetriebnahme benötigt, die Achsen über einen Mikrocontroller auszulesen. 
Das Programm, was auf den ESP8266 aktuell läuft, ermöglicht eine Aufgabe über einen seriellen Monitor. Jede Achse die vorbeirollt wird dabei auf dem Monitor als Text angezeigt.
Hier trat zum ersten Mal das Problem auf: Es sollte möglich sein ohne ständiges 


### Aufbau akustischer Achszähler <!--AP-->



### Programmieren von akustischen Achszähler <!--AP-->
Da das Testen mithilfe eines seriellen Terminals nicht optimal war, kam die Idee des Programmierens eines akustischen Achszählers. 
Beim Detektieren einer Achse einen Ton über einen Beeper (Mini Lautsprecher) ausgegeben. Dieser Prozess hat uns beim Kalibrieren der Sensorik unheimlich geholfen.
Wir nun nicht mehr mit unseren Augen zwischen Bildschirm und unterhalb der Eisenbahn hin und her schauen mussten.