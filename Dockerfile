FROM gradle:8.1-jdk17-alpine AS build

COPY --chown=gradle:gradlew . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17.0.1-jdk-slim

EXPOSE 9090 5005

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/redismultithreading-0.0.1-SNAPSHOT.jar /app/app.jar
ENV _JAVA_OPTIONS '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005'

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
