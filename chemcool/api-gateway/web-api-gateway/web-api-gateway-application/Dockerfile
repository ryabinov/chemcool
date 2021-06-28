FROM openjdk:11-jre

EXPOSE 8080

RUN adduser --system --home /var/cache/bootapp --shell /sbin/nologin bootapp;

COPY /target/web-api-gateway.jar /opt/web-api-gateway.jar

USER bootapp

ENTRYPOINT java -jar /opt/web-api-gateway.jar
