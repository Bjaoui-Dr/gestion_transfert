# Build stage
FROM openjdk:17
COPY target/gestiontransfert-0.0.1-SNAPSHOT.jar gestiontransfert-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "gestiontransfert-0.0.1-SNAPSHOT.jar"]