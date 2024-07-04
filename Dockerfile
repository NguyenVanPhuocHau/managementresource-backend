#FROM openjdk:22-jdk-slim
#LABEL authors="nguye"
#COPY target/managementresource-backend.jar /app/app.jar
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]
#FROM openjdk:22-jdk-slim
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]
FROM openjdk:22-jdk-slim
VOLUME /tmp
COPY target/managementresource-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
