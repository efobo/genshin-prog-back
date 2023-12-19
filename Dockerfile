# Сборка приложения на Spring Boot
FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app
RUN mvn clean package

FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/out/artifacts/genshinProg_jar/*.jar /app/*.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/*.jar"]
