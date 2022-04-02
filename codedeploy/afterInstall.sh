#!/bin/bash
sudo systemctl stop tomcat

sudo rm -rf /var/lib/tomcat9/webapps/ROOT/tomcat
sudo chown tomcat:tomcat /var/lib/tomcat9/webservice/ROOT.war

# cleanup log files
sudo rm -rf /tmp/logs/catalina*
sudo rm -rf /tmp/logs/*.log
sudo rm -rf /tmp/logs/*.txt