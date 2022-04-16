#!/bin/bash

sudo systemctl start tomcat

nohup java -jar /usr/webapp/demoDAO-0.0.1-SNAPSHOT.jar --spring.config.location=application.properties > /dev/null 2> /dev/null < /dev/null & echo $! > ./pid.file &
