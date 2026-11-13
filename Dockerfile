# Stage 1: Werkstatt – hier wird gebaut
FROM eclipse-temurin:21-jdk AS build
WORKDIR /src
COPY app/src/Hallo.java .
RUN javac Hallo.java && jar --create --file app.jar --main-class Hallo *.class

# Stage 2: Lieferwagen – nur das wird ausgeliefert
FROM gcr.io/distroless/java21-debian13:nonroot
COPY --from=build /src/app.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-jar", "/app/app.jar"]
