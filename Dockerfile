FROM gradle:jdk17 AS build

ARG mongodb_uri

WORKDIR /usr/app

COPY . .
RUN ./gradlew build

FROM --platform=linux/amd64 amazoncorretto:17.0.0-alpine

WORKDIR /usr/app

ARG mongodb_uri
ENV MONGODB_URI=$mongodb_uri

COPY --from=build /usr/app/build/libs/Schedulo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/usr/app/app.jar"]