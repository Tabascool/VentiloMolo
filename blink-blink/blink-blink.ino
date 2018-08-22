int time;
bool loul = false;

void setup()
{
  pinMode(13,OUTPUT);
  time = 0;
}

void loop()
{
  if (time == 0)
    loul = false;
  else if (time == 5000)
    loul = true;

  if (loul == false)
    time++;
  else
    time--;

  digitalWrite(13,HIGH);
  delay(time);
  digitalWrite(13,LOW);
  delay(time);
}
