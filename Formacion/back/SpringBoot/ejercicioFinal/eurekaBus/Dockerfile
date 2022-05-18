FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN mkdir /opt/eureka-server
COPY ${JAR_FILE} opt/eureka-server/eurekaBus-0.0.1-SNAPSHOT
ENTRYPOINT ["java", "-jar", "opt/eureka-server/eurekaBus-0.0.1-SNAPSHOT"]
