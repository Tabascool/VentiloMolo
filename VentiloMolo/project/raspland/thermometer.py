import os
import requests, json
#comand = '{"celcius":"10","isMotorActived":"false","humidity":"50"}'
#os.system('curl -i -X POST -H "Content-Type:application/json" -d '+comand+' https://api.mlab.com/api/1/databases/arduiland/collections/Temperature?apiKey=e16CW-yUW4cxtREIjflDYuVWANP6V70W')

r = requests.get("https://api.mlab.com/api/1/databases/arduiland/collections/Setting/5723140afca0fd7cf0f2736c?apiKey=e16CW-yUW4cxtREIjflDYuVWANP6V70W")
test = json.loads(r.text)
print(test['max_temp'])


