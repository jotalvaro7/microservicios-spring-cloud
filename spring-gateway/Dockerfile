#1er Stage
FROM openjdk:11-jdk-slim as builder

WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY src src
RUN ./mvnw clean package -DskipTests

#2do Stage
FROM openjdk:11-jdk-slim

WORKDIR /app
ARG TARGET_FOLDER=/app/target
COPY --from=builder $TARGET_FOLDER/spring-gateway-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=9095
ENV PORT $PORT_APP
EXPOSE $PORT

CMD ["java", "-jar", "spring-gateway-0.0.1-SNAPSHOT.jar"]