#!/bin/bash

#sudo systemctl start tomcat

ls 
echo "Start"
sudo jobs -l
sudo nohup java -jar /tmp/webservice-0.0.1-SNAPSHOT.jar --spring.config.location=jdbc.properties 
sudo jobs -l