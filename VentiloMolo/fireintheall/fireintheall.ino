const int LED_R = 10;
const int LED_O = 11;
const int LED_V = 12;

const int LED_R = 5;
const int LED_O = 6;
const int LED_V = 7;

int timeRed;
int timeGreen;
int timeOrange;

int timeloul = 0;

void setup() {
  // put your setup code here, to run once:
    Serial.begin(9600);// initialisation de la communication
    pinMode(LED_R,OUTPUT);
    pinMode(LED_O, OUTPUT);
    pinMode(LED_V, OUTPUT);
    timeRed = 10000;
    timeGreen = 5000;
    timeOrange = 2000;
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(LED_V,HIGH);
  delay(timeGreen);
  digitalWrite(LED_V,LOW);
  digitalWrite(LED_O,HIGH);
  delay(timeOrange);
  digitalWrite(LED_O,LOW);
  digitalWrite(LED_R,HIGH);
  delay(timeRed);
  digitalWrite(LED_R,LOW);
  timeloul++;
  if (timeloul == 5)
  {
    for (int i = 0; i < 50; i++)
    {
      digitalWrite(LED_V,HIGH);
      delay(200);
      digitalWrite(LED_V,LOW);
      digitalWrite(LED_O,HIGH);
      delay(200);
      digitalWrite(LED_O,LOW);
      digitalWrite(LED_R,HIGH);
      delay(200);
      digitalWrite(LED_R,LOW);
    }

    timeloul = 0;
  }
}
