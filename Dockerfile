FROM openjdk:8-jdk-alpine
MAINTAINER yo3l.m18@gmail.com
RUN addgroup -S spring
RUN adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","/application.jar"]
EXPOSE 8001