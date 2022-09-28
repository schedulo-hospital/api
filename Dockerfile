FROM gradle:jdk17

# RUN microdnf install findutils

ARG mongodb_uri

WORKDIR /usr/app

ENV MONGODB_URI=$mongodb_uri

COPY . .
RUN ./gradlew build

RUN ls -la
COPY ./build/libs/Schedulo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/usr/app/app.jar"]