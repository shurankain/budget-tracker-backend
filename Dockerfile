FROM openjdk:17
ARG JAR_FILE=build/libs/*RELEASE.jar
COPY ${JAR_FILE} budget-tracker-backend.jar
ENTRYPOINT ["java","-jar","/budget-tracker-backend.jar"]