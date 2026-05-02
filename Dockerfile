# ---------- BUILD STAGE ----------
FROM gradle:8.14.3-jdk21 AS builder
WORKDIR /app
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN chmod +x gradlew
RUN ./gradlew dependencies
COPY src ./src
RUN ./gradlew bootJar
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]