FROM adoptopenjdk/openjdk11:alpine
VOLUME /tmp
EXPOSE 8889
COPY target/app-back-cliente-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
