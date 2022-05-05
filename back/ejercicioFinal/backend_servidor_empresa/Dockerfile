FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN mkdir /opt/backEmpresa
COPY ${JAR_FILE} opt/backEmpresa/ejercicioFinal-0.0.1-SNAPSHOT
ENTRYPOINT ["java", "-jar", "opt/backEmpresa/ejercicioFinal-0.0.1-SNAPSHOT"]


