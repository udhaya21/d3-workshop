FROM maven:3.9-amazoncorretto-21

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src/ ./src/
RUN mvn clean package -DskipTests

EXPOSE 3000

CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]