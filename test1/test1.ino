void setup() {
  //initialisation des pins
  Serial.begin(9600);// initialisation de la communication
  Serial.println("Communication initialisée");// envoi d'un message
}

void loop() {
  unsigned long tempsFin;
  unsigned long tempsDepart = micros();

  Serial.println(tempsFin - tempsDepart);
  tempsFin = micros();
  delay(500);
}
