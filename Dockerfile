FROM openjdk:17

EXPOSE 8085

WORKDIR /app

COPY /target/jobBoardApi-0.0.1-SNAPSHOT.jar  /app/jobBoardApi-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/app/jobBoardApi-0.0.1-SNAPSHOT.jar"]
