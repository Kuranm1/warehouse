# Use a slim OpenJDK 17 image
FROM openjdk:17-slim

# Create a directory for the application
WORKDIR /app

# Copy the JAR file from the build context (current directory)
COPY target/*.jar app.jar

# Expose the port where the application listens (usually 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]