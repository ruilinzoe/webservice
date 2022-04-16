#!/bin/bash

#sudo systemctl start tomcat

nohup java -jar /tmp/webservice-0.0.1-SNAPSHOT.jar --spring.config.location=jdbc.properties > /dev/null 2> /dev/null < /dev/null & echo $! > ./pid.file &
