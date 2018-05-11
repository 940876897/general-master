#!/bin/bash
#filename deploy-api.sh
set -x
export JAVA_HOME=/usr/lib/jvm
export TOMCAT_HOME=/opt/ucmed-gereral-test
api_pid=$(ps -ef|grep ucmed-gereral|grep gereral-test|awk '{print $2}')
for temp_pid in ${api_pid}
do 
    kill -9 ${temp_pid}
done
war_file="/home/ucmed/gerenal/ROOT.war"
if [ -f "$war_file" ];
then
    echo "War file exists, deploy and start the server."
    rm -rf ${TOMCAT_HOME}/webapps/ROOT/
    rm -f ${TOMCAT_HOME}/webapps/ROOT.war
    mv -f ${war_file} ${TOMCAT_HOME}/webapps/ROOT.war
else
    echo "War file not exists, restart the server."
fi
set +x
/bin/bash ${TOMCAT_HOME}/bin/startup.sh

