FROM openjdk:17

WORKDIR /app
ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} .

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/demo-0.0.1-SNAPSHOT.jar"]
