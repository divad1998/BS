# Stage 1: Build the application
FROM maven:3.9-amazoncorretto-21-al2023 AS build
WORKDIR /

# Copy the pom.xml and source code
COPY pom.xml .
COPY src src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Set up the runtime environment
FROM eclipse-temurin:21.0.2_13-jdk-alpine
WORKDIR /
COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
