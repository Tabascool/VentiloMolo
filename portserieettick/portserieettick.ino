void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  Serial.println("Coucou! Ca te plairais de manger de mon bon miel?");
  // Si la Arduino a reçu un message de notre part
  if (Serial.available())
  {
    // On lit le message et on l'affiche
    int lu = Serial.read();
    Serial.println(lu);
  }
  else
  {
    Serial.println("HELAS! il n'y a rien :(");
  }

  delay(2000);
}
