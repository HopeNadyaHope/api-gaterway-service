FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
EXPOSE 8888
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} api-gaterway-service.jar
ENTRYPOINT ["java","-jar","/api-gaterway-service.jar"]