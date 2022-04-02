#!/bin/bash
sudo systemctl stop tomcat

sudo rm -rf /tmp/webservice-0.0.1-SNAPSHOT.jar
sudo chown tomcat:tomcat /tmp/webservice-0.0.1-SNAPSHOT.jar

# cleanup log files
sudo rm -rf /tmp/logs/catalina*
sudo rm -rf /tmp/logs/*.log
sudo rm -rf /tmp/logs/*.txt