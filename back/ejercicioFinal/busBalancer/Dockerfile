FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN mkdir /opt/balanceador-server
COPY ${JAR_FILE} opt/balanceador/busBalancer-0.0.1-SNAPSHOT
ENTRYPOINT ["java", "-jar", "opt/balanceador/busBalancer-0.0.1-SNAPSHOT"]
