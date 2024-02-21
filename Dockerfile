FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/hls1-0.1.0-SNAPSHOT.jar app/target/
ENTRYPOINT ["java","-jar","/target/hls1-0.1.0-SNAPSHOT.jar"]