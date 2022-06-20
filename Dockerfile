FROM openjdk:18-alpine

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
ENV PORT 8888
RUN ./mvnw dependency:go-offline
COPY src ./src
EXPOSE $PORT

CMD ["./mvnw", "spring-boot:run"]