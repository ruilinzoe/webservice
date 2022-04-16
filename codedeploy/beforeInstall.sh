#!/bin/bash

sudo systemctl stop tomcat
sudo kill $(sudo lsof -t -i:8080)
sudo rm -rf /tmp/webservice-0.0.1-SNAPSHOT.jar