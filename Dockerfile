FROM tomcat:8.5.15-jre8-alpine

COPY general-master/general-parent/general/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

RUN cd /usr/local/tomcat/webapps \
    && rm -rf ROOT \
    && mkdir ROOT \
    && unzip -o ROOT.war -d ROOT \
    && rm ROOT.war