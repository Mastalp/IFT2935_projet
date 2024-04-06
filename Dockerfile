FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /app/target/ift2935_projet.jar /app/ift2935_projet.jar
EXPOSE 8080
CMD ["java", "-jar", "ift2935_projet.jar"]