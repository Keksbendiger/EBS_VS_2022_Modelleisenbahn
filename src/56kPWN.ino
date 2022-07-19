void setup()
{
 // configure hardware timer2 to generate a fast PWM on OC2B (Arduino digital pin 3)
 // set pin high on overflow, clear on compare match with OCR2B
 
 TCCR2A = 0x23;
 TCCR2B = 0x0A;  // select timer2 clock as 16 MHz I/O clock / 64 = 250 kHz
 OCR2A = 34;  // top/overflow value is 249 => produces a 1000 Hz PWM
 pinMode(3, OUTPUT);  // enable the PWM output (you now have a PWM signal on digital pin 3)
 OCR2B = 17;  // set the PWM to 50% duty cycle
}

void loop() {
  delay(100);
}
