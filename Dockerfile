FROM openjdk:19-jdk
COPY target/ensayos_server.jar .
expose ${PUERTO_API:-8080}
ENTRYPOINT ["java", "-jar", "ensayos_server.jar"]