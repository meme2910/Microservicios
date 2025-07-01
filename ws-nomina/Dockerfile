# -----------------------------------------------------------------------------
# Dockerfile para el microservicio ws-nomina
#
# Este archivo define los pasos para construir una imagen Docker que ejecuta
# el microservicio ws-nomina, desarrollado en Java con Spring Boot.
#
# Pasos principales:
# 1. Usa una imagen base ligera de OpenJDK 17 para ejecutar aplicaciones Java.
# 2. Establece el directorio de trabajo en /app dentro del contenedor.
# 3. Copia el archivo JAR generado por Maven desde la carpeta target al contenedor.
# 4. Expone el puerto 8080, que es el configurado en application.properties.
# 5. Define el comando de inicio para ejecutar el microservicio.
#
# Instrucciones de uso:
# - Construye la imagen desde la raíz del proyecto para asegurar que el JAR exista:
#     docker build -f ws-nomina/Dockerfile -t ws-nomina .
# - Ejecuta el contenedor:
#     docker run -p 8080:8080 ws-nomina
# -----------------------------------------------------------------------------

# Imagen base de OpenJDK para ejecución de aplicación
FROM openjdk:17-jdk-slim

# Generación de espacio de trabajo
WORKDIR /app

# Copia de JAR generado del microservicio
COPY /target/ws-nomina-0.0.1-SNAPSHOT.jar ws-nomina.jar

# Expone el puerto 8080 que está configurado en application.properties
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT [ "java","-jar","ws-nomina.jar"]