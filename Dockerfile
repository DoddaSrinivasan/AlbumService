FROM openjdk:8-jdk-alpine
VOLUME /tmp
VOLUME /etc/images
COPY ./build/libs/albumservice-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker" ,"-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]