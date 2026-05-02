FROM gradle:8.14.3-jdk21 AS builder
WORKDIR /app
COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon --build-cache -x test || return 0
COPY src ./src
RUN ./gradlew bootJar --no-daemon --build-cache -x test
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]