FROM maven:3.6.3-openjdk-15 AS MAVEN_BUILD
COPY pom.xml /
COPY src /src/
RUN mvn package

FROM openjdk:15-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=MAVEN_BUILD /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]