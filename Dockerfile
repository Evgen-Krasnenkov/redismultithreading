FROM gradle:8.1-jdk17-alpine AS build

COPY --chown=gradle:gradlew . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17.0.1-jdk-slim

EXPOSE 9090 5005

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/redismultithreading-0.0.1-SNAPSHOT.jar /app/app.jar
ADD https://raw.githubusercontent.com/Evgen-Krasnenkov/redismultithreading/master/src/main/resources/application.yaml /app/application.yaml

ENV SPRING_CONFIG_LOCATION="file:/app/application.yaml"
ENV JAVA_OPTS '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005'

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar --spring.config.location=$SPRING_CONFIG_LOCATION"]
