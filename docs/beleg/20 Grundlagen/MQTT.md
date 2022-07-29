## MQTT <!--CH-->
### Protokoll
OASIS spezifiziert die Protokolle MQTT 3.1, MQTT 3.1.1 und MQTT 5 (https://docs.oasis-open.org/mqtt/mqtt/v5.0/mqtt-v5.0.html).

MQTT ist ein Client Server publish/subscribe messaging transport protocol.
Der Fokus liegt auf Leichtgewichtigkeit, Offenheit, Einfachheit und einfacher Implementierung.
Dadurch ist das Protokoll für verschiedenste Situationen geeignet,
beispielsweise in eingeschränkten Umgebungen wie etwa Maschine-zu-Maschine-Kommunikation (M2M) oder Internet of Things (IoT) Kontexten,
bei denen nur wenig Programmcode geschrieben werden oder die Netzwerkbandbreite geschont werden soll.

Normalerweise wird das Protokoll auf TCP/IP aufgesetzt, kann jedoch mit anderen Protokollen verwendet werden, insofern diese ebenfalls verlustfrei und bidirektional sind.
Der Overhead beim Transport und beim Verbindungsaufbau ist minimal.
Bei ungeplanten Verbindungsabbrüchen können interessierte Teilnehmer über das Protokoll informiert werden.
Der Nachrichtentransport ist unabhängig vom Payload.
Das publish/subscribe message pattern ermöglicht eine 1-zu-N-Verteilung der Nachrichten und entkoppelt den Nachrichtenverkehr von spezifischen Anwendungen.
Es werden drei verschiedene Übertragungsmodi (quality of service) unterstützt.

### Einsatzorte
Neben privaten IoT-Anwendungen wird MQTT auch in vielen kommerziellen Umgebungen eingesetzt.
Beispiele hierfür sind:
* BMW Car-Sharing
* autonome Drohnen von Matternet (Transport von medizinischen Gütern)
* Überwachung von Stromerzeugung mehrerer Kraftwerke von Celikler Holding
* Überwachung und Steuerung von Smart Home Anwendungen (IBM) und Sicherheitssystemen (eFon)
* Sammeln und überwachen von Sensordaten in der petrochemischen Industrie
* Smart Kitchen Anwendungen (CASO Design)
* DB Railway System (!!)

(https://mqtt.org/use-cases/)

### Broker
Der MQTT-Broker ist der Server, mit dem sich alle Clients verbinden, um Nachrichten zu senden oder zu empfangen.
Jede Nachricht verfügt über ein sogenanntes Topic, welches als Nachrichtenkanal verstanden werden kann.
Diese Topics werden von interessierten Clients beim Broker abonniert.
Ausschließlich Nachrichten abonnierter Topics werden vom Server an die jeweiligen Clients weitergeleitet.
Ein Anmelden oder Registrieren der Topics beim Broker ist nicht erforderlich.
Einige Implementierungen verfügen jedoch über erweiterte Sicherheitsfeatures, unter anderem auch Whitelists für Topics.