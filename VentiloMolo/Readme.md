Project:
--------  

Create an automatic fan which activate regarding the current temperature.  
On the Android application, display in graphic the motor's activity regarding temperature.  
Configure the temperature's limite from the application to the Arduino.  

*(FR: Créer un ventilateur automatique qui se déclenche en fonction de la température environnante.  
Afficher graphiquement les données de l'activité du moteur par rapport à la température sur l'application Android native.  
La limite de température est configurée via l'application.)*  

Material:
---------

**Arduino UNO (ou MEGA)**:  
Temperature sensor    
Motor (continue)
Shield motor
Zigbee module
Otocoupler (for motor alimentation)  

**Raspberry Pi 2 B+**:  
Zigbee module  

**Android**:  
Native application   

**Server**:  
MongoLab    

Communication:
--------------

Zigbee  
WiFi  

Objectives:
-----------

**Configuration:**    
Configuration of temperature's limit (Android -> Raspberry -> Arduino)  

**Datas:**    
Temperature datas (Sensor -> Arduino)   
Motor's activity datas (Motor -> Arduino)  

**Database:**    
Send temperature datas to databse (Arduino -> Raspberry -> Server)  
Send motor datas to database (Arduino -> Raspberry -> Server)   

**Application:**    
Graphics of motor and temperature datas (Android -> Server -> Android)   

Programming:
------------

C   
Python  
Android  

Team:
-----

Julien Cassone (https://github.com/julivea)  
Antoine Zazzera (https://github.com/antoinezaz)  
Florent Blot (https://github.com/Gitdefllo)  
