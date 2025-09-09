# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file to the working directory
COPY target/mortgage-0.0.1-SNAPSHOT.jar mortgage-service.jar

# Expose the port the application runs on
EXPOSE 8086

# Run the JAR file
ENTRYPOINT ["java", "--enable-preview", "-jar", "mortgage-service.jar"]