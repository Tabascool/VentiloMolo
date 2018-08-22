#include <XBee.h>
#include <SoftwareSerial.h>

XBee xbee = XBee();

uint8_t payload[30];

char data[6]="hello";

void setup() {
  
  xbee.begin(38400);
  
  Serial.begin(38400);
  Serial.println("Arduino. Will send packets.");
}

void loop() {
  
  delay(5000);
    
  // 64-bit addressing: This is the SH + SL address of remote XBee
  XBeeAddress64 addr64 = XBeeAddress64(0x00000000, 0x0000FFFF);
  // unless you have MY on the receiving radio set to FFFF, this will be received as a RX16 packet
  
  for (int i; i<sizeof(data); i++)
    payload[i]=(uint8_t)data[i];
  
  // in this way, we know the exact size of the payload
  Tx64Request tx = Tx64Request(addr64, (uint8_t*)data, sizeof(data));
  
  // Send your request
  xbee.send(tx);

  TxStatusResponse txStatus = TxStatusResponse();
}
