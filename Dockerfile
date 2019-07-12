FROM payara/micro:5-SNAPSHOT

MAINTAINER "JMAR"

COPY target/hello-javaee8.war /opt/payara/deployments
