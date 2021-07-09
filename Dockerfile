FROM openjdk:11.0.11

# Copy local project files
COPY mvnw pom.xml ./
COPY src src
COPY .mvn .mvn

# Build project on image
RUN ./mvnw install -DskipTests
RUN cp /target/rating-app-backend-0.0.1-SNAPSHOT.jar app.jar

# Remove target doc after generating .jar
RUN rm -rf ./target

ENTRYPOINT ["java","-jar","app.jar"]