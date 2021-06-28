FROM openjdk:11-jre

EXPOSE 8761

RUN adduser --system --home /var/cache/bootapp --shell /sbin/nologin bootapp;

COPY /target/theory.jar /opt/theory.jar

USER bootapp

ENTRYPOINT java -jar /opt/theory.jar
