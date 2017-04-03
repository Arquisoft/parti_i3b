# Parti_i3b

# Run instructions

In order to run the application you need to download and install [mongoDB](https://www.mongodb.com/dr/fastdl.mongodb.org/win32/mongodb-win32-x86_64-2008plus-ssl-3.4.2-signed.msi/download). You must create an empty folder called "data\db" in the root of the partition where you installed mongo (Example: "C:\data\db"). 
Besides, you will have to download [kafkaStream 2.11](https://kafka.apache.org/downloads). We will provide a script called startup.bat which you need to copy into the kafka download folder. After executing this script you will be able to execute mvn springboot:run.

To run Gatling tests just run 
     $mvn gatling:execute
     
# Authors

* Gonzalo de la Cruz Fernández (UO244583) @gdelacruzfdez
* Oriol Invernón Llaneza (UO245303) @OriolInvernonLlaneza
* Adrian Mirón Cao (UO244843) @mironcao
* Juan Francisco Piñera (UO244707) @juanfpo96
* Paula Tuñón Alba (UO244709) @PaulaTAlba
* Guillermo Rodríguez González (UO245104) @guille
