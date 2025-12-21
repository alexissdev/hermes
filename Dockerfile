# Build stage
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

# Copiar Gradle wrapper y proyecto
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src

RUN chmod +x gradlew
RUN ./gradlew clean bootJar -x test

# Run stage
FROM eclipse-temurin:17-jdk-jammy
