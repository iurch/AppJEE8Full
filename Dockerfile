FROM payara/micro:5-SNAPSHOT

MAINTAINER "JMAR"

COPY target/app.war /opt/payara/deployments
# COPY target/hello-javaee8.war /opt/payara/deployments

# mvn clean package && docker build -t appjee8/app:1.0 .
# docker rm -f app || true && docker run -it -p 8080:8080 --name app appjee8/app:1.0

# http :8080/app/api/hello