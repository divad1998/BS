FROM eclipse-temurin:21.0.2_13-jdk-alpine
VOLUME /tmp
COPY target/bs-api.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]