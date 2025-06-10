#Imagen Base de OpenJDK para ejecución de aplicación
FROM openjdk:17-jdk-slim

#Generacion de espacio de Trabajo
WORKDIR /app

#Copia de JAR generado del Microservicio
COPY /target/ws-nomina-0.0.1-SNAPSHOT.jar ws-nomina.jar

#Expone el puerto 8080 que esta configurado aplication.properties
EXPOSE 8080

#Comando de ejecución
ENTRYPOINT [ "java","-jar","ws-nomina.jar"]