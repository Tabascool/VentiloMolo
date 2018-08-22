// NOTE FOR SENSOR:
// Connect pin 1 (on the left) of the sensor to +5V
// NOTE: If using a board with 3.3V logic like an Arduino Due connect pin 1
// to 3.3V instead of 5V!
// Connect pin 2 of the sensor to whatever your DHTPIN is
// Connect pin 4 (on the right) of the sensor to GROUND
// Connect a 10K resistor from pin 2 (data) to pin 1 (power) of the sensor

#include <DHT.h>
#include <Wire.h>

#define DHTPIN 6
#define DHTTYPE DHT11
#define DATAS_ADDRESS 0x14

DHT dht(DHTPIN, DHTTYPE);
const int LED_TEMP = 12;

float temperature;
float limitTemperature = 23.00;
float humidity;
int   ledActive = 0;
int   dataReceived = 0;

void setup() {
  Serial.begin(9600);
  Serial.println("Start program...\n");
  
  Wire.begin(DATAS_ADDRESS);
  Wire.onReceive(receiveData);
  Wire.onRequest(sendData);
  
  pinMode(LED_TEMP, OUTPUT);
  dht.begin();
}

void loop() {
  delay(2000);

  humidity = dht.readHumidity();
  temperature = dht.readTemperature();

  if (isnan(humidity) || isnan(temperature)) { 
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  Serial.print("Humidity: ");
  Serial.print(humidity);
  Serial.print(" %\t");
  Serial.print("Temperature: ");
  Serial.print(temperature);
  Serial.print(" *C ");

  Serial.println("");
  isHot(temperature);
}

void receiveData(int byteCount) {
    while(Wire.available()) {
        dataReceived = Wire.read();
        Serial.print("Data received : ");
        Serial.println(dataReceived);
        
        if (dataReceived != 98
          && dataReceived != 99
          && dataReceived != 100) {
            limitTemperature = dataReceived;
        }
    }
}

void sendData() {
    float dataResponse;
    switch (dataReceived) {
      case 98:
        dataResponse = temperature;
        break;
      case 99:
        dataResponse = humidity;
        break;
      case 100:
        dataResponse = ledActive;
        break;
    }
    Wire.write((byte) dataResponse);
}

void isHot(float temp) {
  if (temp > limitTemperature) {
    ledActive = 1;
    digitalWrite(LED_TEMP, HIGH);
  } else {
    ledActive = 0;
    digitalWrite(LED_TEMP, LOW);
  }
}

