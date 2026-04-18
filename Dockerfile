FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY project/pom.xml ./pom.xml
COPY project/src ./src
RUN mvn package -DskipTests --batch-mode

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/jndarray-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-cp", "app.jar", "com.groupe8.ttykm.Demo"]
