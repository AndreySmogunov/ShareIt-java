FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/shareIt-server-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]