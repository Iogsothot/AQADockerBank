FROM gradle:8.7-jdk21 as builder
WORKDIR /home/gradle
COPY . .
RUN gradle jar

FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /home/gradle/build/libs/AQADockerBank-1.0-SNAPSHOT.jar .
CMD ["java", "-jar", "AQADockerBank-1.0-SNAPSHOT.jar"]
