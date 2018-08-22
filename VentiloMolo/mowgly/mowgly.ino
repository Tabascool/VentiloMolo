unsigned long ms;

void setup() {
  Serial.begin(9600);
  while(!Serial){
    Serial.println("Ok init finish");
  }
}

void loop() {
  
  ms = millis(); // get the milisecond since the program is launched
  Serial.print(ms); // print ms on serial console
  Serial.print("mowgly");
  delay(1000);
}
