FROM openjdk:11-jre

EXPOSE 8888

RUN adduser --system --home /var/cache/bootapp --shell /sbin/nologin bootapp;

COPY /target/config-service.jar /opt/config-service.jar

USER bootapp

ENTRYPOINT ["java","-jar","/opt/config-service.jar"]
