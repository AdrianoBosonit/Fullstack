FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN mkdir /opt/backWeb
COPY ${JAR_FILE} opt/backWeb/ejercicioFinal-0.0.1-SNAPSHOT
ENTRYPOINT ["java", "-jar", "opt/backWeb/ejercicioFinal-0.0.1-SNAPSHOT"]
