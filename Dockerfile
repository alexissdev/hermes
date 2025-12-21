# Build stage
FROM gradle:8.3-jdk17 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle clean bootJar -x test

# Run stage
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/build/libs/hermes.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]