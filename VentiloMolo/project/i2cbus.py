import os, smbus, time, requests, json

bus = smbus.SMBus(1)
address_datas = 0x14

def getTemperature():
        return bus.read_byte_data(address_datas, 98)

def getHumidity():
        return bus.read_byte_data(address_datas, 99)

def getMotorActivity():
        return bus.read_byte_data(address_datas, 100)

def setTemperatureLimit(limit):
        bus.write_byte(address_datas, limit)

setTemperatureLimit(24)

while True:
        tmp = getTemperature()
        print("Temperature: " + str(tmp) + " degrees")
        hum = getHumidity()
        print("Humidity: " + str(hum) + " %")
        mot = getMotorActivity()
        print("Motor activity: " + str(mot))

        req = requests.get("https://api.mlab.com/api/1/databases/arduiland/collections/Setting/5723140afca0fd7cf0f2736c?apiKey=e16CW-yUW4cxtREIjflDYuVWANP6V70W
        body = json.loads(req.text)
        setTemperatureLimit(body["max_temp"])

        time.sleep(5)

#comand = '{"celcius":"10","isMotorActived":"false","humidity":"50"}'
#os.system('curl -i -X POST -H "Content-Type:application/json" -d '+comand+' https://api.mlab.com/api/1/databases/ar$
