FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/shareIt-gateway-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]