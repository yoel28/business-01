FROM openjdk:8-jdk-alpine
MAINTAINER yo3l.m18@gmail.com
RUN apk add --no-cache bash
RUN apk add --no-cache maven

ARG USER="spring"
ARG HOME="/home/${USER}"
ARG APP_DIR="${HOME}/app"
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

RUN mkdir -p ${APP_DIR}
WORKDIR ${APP_DIR}
COPY pom.xml ${APP_DIR}
COPY src ${APP_DIR}/src

##################
### LOCAL TEST ###
##################
#COPY target/*.jar ${APP_DIR}/target/application.jar
##################
RUN mvn package

RUN cp target/*.jar ${HOME}/application.jar
RUN rm -rf ${APP_DIR}
RUN rm -rf ${HOME}/.m2

RUN addgroup -S ${USER}
RUN adduser -S ${USER} -G ${USER}
RUN chown -R ${USER}:${USER} ${HOME}

ENTRYPOINT java -jar ${HOME}/application.jar
USER ${USER}:${USER}
EXPOSE 8001