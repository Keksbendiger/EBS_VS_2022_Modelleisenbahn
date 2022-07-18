# EBS Datensammlung
## Hardware Constraints
### ESP8266
Wird von FreeRTOS nicht unterstützt. Es gibt eine SDK die in einem Terminal läuft, hängt aber grundsätzlich an der Fragilität der ESP

### Arduino Uno
Generell die Bessere Wahl. Vielleicht eine ESP als reines WIFI Modul betreiben und via FreeRTOS die Kommunikation erledigen.
Der FreeRTOS Scheduler braucht 20% des Programmspeichers alleine und 8% des dynamischen Speichers

### Good Guides
https://circuitdigest.com/microcontroller-projects/arduino-freertos-tutorial1-creating-freertos-task-to-blink-led-in-arduino-uno

### Generell über FreeRTOS
xTaskDelay benutzen, auch wenn es komplexer zu implementieren ist. Sorgt für eine korrekte Periodizität der Tasks
vTaskSuspend und vTaskResume benutzen um ein auffüllen des Schedulers zu verhindern.

### Offene Fragen
Performance von MQTT? Wie lange dauert die Kommunikation? Blockiert der MQTT Task oder müssen die anderen Tasks von Hand blockiert werden
Ist das überhaupt nötig? Oder kommt MQTT mit den kurzen Unterbrechungen zurecht?
Wie groß sollen die MQTT Packete werden? aka nach wie vielen Achsen die erste Meldung, nach welcher Zeit. Vielleicht alle 25 ms eine MQTT Botschaft?
