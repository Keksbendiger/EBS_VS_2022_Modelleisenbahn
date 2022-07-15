# EBS Datensammlung
## Hardware Constraints
### ESP8266
Wird von FreeRTOS nicht unterstützt. Es gibt eine SDK die in einem Terminal läuft, hängt aber grundsätzlich an der Fragilität der ESP

### Arduino Uno
Generell die Bessere Wahl. Vielleicht eine ESP als reines WIFI Modul betreiben und via FreeRTOS die Kommunikation erledigen.
Der FreeRTOS Scheduler braucht 20% des Programmspeichers alleine und 8% des dynamischen Speichers

### Good Guides
https://circuitdigest.com/microcontroller-projects/arduino-freertos-tutorial1-creating-freertos-task-to-blink-led-in-arduino-uno
