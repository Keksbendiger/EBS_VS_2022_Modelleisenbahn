### Aufbau Gleisplan <!-- NJ-->
Die Grundidee ist es mehrere Züge auf demselben Schienennetz fahren zu lassen,
ohne das diese miteinander kollidieren. In unseres Versuchsaufbaus ist es der Plan drei Züge
gleichzeitig fahren zu lassen und das diese sich gegenseitig berücksichtigen und ausweichen.
Hierfür musste die entsprechende Anzahl an Ausweichgleisen kalkuliert werden. Um den die Anzahl
der Züge etwas variable zu halten wurden sich für insgesamt drei Ausweichgleise entschieden. 

![Gleisplan](../bilder/Gleisplan.png)

Mit diesem Gleisplan ist es möglich bis zu vier Züge fahren zu lassen, ohne dass diese ständig
stehen bleiben.
Der untere linke Gleisabschnitt ist anders aufgeteilt als die anderen, da an diesen zwei Weichen
nur insgesamt vier Achszähler befestigt sind. Dies hat die Ursache, dass der unterste Gleisabschnitt
zu kurz ist, um einen ganzen Zug zu beherbergen. Daher wird dieser Block als Durchfahrtsblock deklariert,
d.h. in diesem Block wird/sollte nie ein Zug zum Stehen kommen. 

#### Die Auswahl der Züge und die damit verbundene Länge der Ausweichgleise
Um das Projekt realistisch zu halten, sollen nicht nur die Loks über die Schienen fahren, sondern ganze Züge, also Loks mit
mehreren Wagons. Hierfür braucht es wiederum längere Ausweichgleise. Daher wurden sich für folgende Züge entscheiden:

* ICE (Triebwagen + 4 Passagierwagons, Priorität 1) => insgesamt 24 Achsen
* BR110 (Triebwagen + 2 Passagierwagons, Priorität 2) => insgesamt 12 Achsen
* BR142 (Triebwagen + 4 Güterwagons, Priorität 3) => insgesamt 14 Achsen

Der BR110 soll in dem unteren linken Gleisabschnitt im Kreis fahren, aber immer warten, wenn ein Zug mit höherer Priorität
durch den kurzen unteren Abschnitt fährt. Wenn der ICE und der BR110 beide in den gleichen Abschnitt einfahren wollen,
gewinnt bei der Entscheidung der Zuweisung der ICE, weil dieser die höhere Priorität hat.

Jeder Gleisabschnitt verfügt immer über einen Einfahrts- und Ausfahrtssensor, welcher die im Code hinterlegten Achsen zählt 
und vergleicht.
Sollte ein Zug über jeweils einen Ausfahrts- und einen Einfahrtssensor fahren und diese melden
unterschiedliche Werte, wird das System gestoppt und alle Züge bleiben stehen. 