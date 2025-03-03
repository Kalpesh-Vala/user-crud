# Build Stage
FROM maven:3-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the JAR file (Skipping Tests)
RUN mvn clean package -DskipTests

# Runtime Stage
FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
