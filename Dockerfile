FROM openjdk:11-jre

ARG  OPENAPI_USER=xxx
ARG  OPENAPI_PASSWORD=xxx

COPY target/*.jar /opt/webapp.jar

CMD java -Dserver.port=$PORT $JAVA_OPTS -jar /opt/webapp.jar