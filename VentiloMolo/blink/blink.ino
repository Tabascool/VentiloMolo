int led = 11;
volatile int state = HIGH;

void setup() {
  //Serial.begin(9600);
  pinMode(led, OUTPUT);
  attachInterrupt(0, blink, CHANGE); // when the button is pushed, trigger the blink method
}

void loop() {
  //Serial.print(state);
  digitalWrite(led, state);
  delay(100);
}

void blink() {
  //Serial.println("change");
  state = !state; //change state (HIGH or LOW)
}

