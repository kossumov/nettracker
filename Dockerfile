FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy source code
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Grant execution permission to Gradle
RUN chmod +x gradlew

# Build
RUN ./gradlew build -x test

# Create image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar library-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "library-service.jar"]