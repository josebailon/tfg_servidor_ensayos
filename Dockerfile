FROM openjdk:19-jdk
COPY target/ensayos_server.jar .
expose 8080
ENTRYPOINT ["java", "-jar", "ensayos_server.jar"]