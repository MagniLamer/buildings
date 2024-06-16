FROM gradle:8.8.0-jdk17 AS build

ENV GRADLE_USER_HOME=/home/gradle/.gradle

WORKDIR /app

COPY . .

RUN gradle build --no-daemon

FROM amazoncorretto:17-alpine

COPY --from=build /app/build/libs/*.jar /app/app.jar

WORKDIR /app

CMD ["java", "-jar", "app.jar"]
