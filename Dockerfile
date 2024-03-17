# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 as build

# Copy source code to the container
COPY src /home/app/src
COPY pom.xml /home/app

# Package the application
RUN mvn -f /home/app/pom.xml clean package

# Stage 2: Create the Docker container with the compiled application
FROM openjdk:17-oracle

# Copy the built jar file from the build stage
COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar

# Expose the port the app runs on
EXPOSE 8082

# Run the jar file 
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
