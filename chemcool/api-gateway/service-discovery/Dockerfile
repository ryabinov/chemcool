FROM openjdk:11-jre

EXPOSE 8761

RUN adduser --system --home /var/cache/bootapp --shell /sbin/nologin bootapp;

COPY /target/service-discovery.jar /opt/service-discovery.jar

USER bootapp

ENTRYPOINT ["java","-jar","/opt/service-discovery.jar"]
