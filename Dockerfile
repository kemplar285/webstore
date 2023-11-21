# Build
FROM maven:3.8.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Run
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/webstore-0.0.1-SNAPSHOT.jar ./backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend.jar"]