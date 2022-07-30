
## Lessons Learned
- RPi ohne RTC und ohne NTP (weil Nutzung als AP) kritisch - großer Aufwand
- Niclas hat schon wieder den Alexa-Skill nicht gemacht

### Elektronische Bauteile immer mit Reserve bestellen
Wir haben alle Bauteile auf die kalkulierte Anzahl bestellt, welches ein Fehler war, weil bei dem
Zusammenbau der Weichen das eine oder andere Beinchen der Bauteile abgebrochen ist und das Bauteil somit unbrauchbar 
war. 

#### Lösung des Problems
Immer Ersatz bei kleineren Bauteilen bestellen oder bei Bauteilen, welche schnell kaputtgehen.


### Bauteile richtig mit den Kabeln verlöten
Die Bauteile wie der IR-Sensor wurden per Jumper-Wire angeschlossen. Wie bekannt können diese Kabel verantwortlich sein
für Wackelkontakte oder abrutschende Kabel. Oftmals haben während der Versuche ein oder mehrere Sensoren nicht funktioniert,
da an einem Sensor oder Verbindungskabel sich ein Jumper-Wire gelöst hat.

#### Lösung des Problems
Alle Bauteile müssen nach der Anbringung an der Weiche fest mit den Kabeln verlötet und isoliert werden.

### Transistor richtig betreiben
Als wir die Weichenschaltung aufgebaut hatten und diese testen wollten funktionierte die trotz richtigem Schaltplan nicht.
Nach unzähligem Testen haben wir herausgefunden, dass wir den Transistor versehentlich mit Wechselspannung betrieben hatten.
Betreibt man einen Transistor mit Wechselspannung versteht dieser nämlich nur Bahnhof.

#### Lösung des Problems
Den Transistor nur mit Gleichspannung betreiben.