FROM openjdk:11.0.11
VOLUME /tmp
ADD src/main/resources/db/migration/V1_0_0__db_migration.sql /docker-entrypoint-initdb.d/
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]