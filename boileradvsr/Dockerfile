FROM openjdk:11.0.18-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} libinfosystem.jar
ENTRYPOINT ["java","-jar","/libinfosystem.jar"]