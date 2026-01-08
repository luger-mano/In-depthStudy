FROM maven:3.8-amazoncorretto-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

RUN mvn package -DskipTests

FROM amazoncorretto:17-alpine

WORKDIR /app

COPY --from=build /app/target/eurekaservice-0.0.1-SNAPSHOT*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]