FROM maven:3.8.4-openjdk-17
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true
FROM openjdk:17-jre-slim
WORKDIR /app
COPY --from=build /app/target/ift2935_project.jar /app/
EXPOSE 32678
CMD ["java", "-jar", "ift2935_projet.jar"]