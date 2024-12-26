# Use an OpenJDK base image
FROM ibm-semeru-runtimes:open-17-jdk as builder

# Set the working directory in the container
WORKDIR /app

# Define a build argument for the application name
ARG APP_NAME=noop-application

# Copy the JAR file into the container
COPY ${APP_NAME}/build/libs/${APP_NAME}-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (default 8080 for Spring Boot)
EXPOSE 8080

# Set the command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
