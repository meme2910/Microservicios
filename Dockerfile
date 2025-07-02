# -----------------------------------------------------------------------------
# Dockerfile multi-módulo parametrizable por microservicio
#
# Este archivo define los pasos para construir una imagen Docker que ejecuta
# uno de los microservicios del proyecto (por ejemplo, ws-nomina o ws-trabajador),
# permitiendo seleccionar el microservicio a compilar y empaquetar mediante un argumento.
#
# Pasos principales:
# 1. Usa una imagen base de Maven con OpenJDK 17 para compilar los microservicios.
# 2. Permite seleccionar el módulo a compilar mediante el argumento MODULE.
# 3. Copia los archivos pom.xml y descarga las dependencias para optimizar la cache.
# 4. Copia el código fuente de los microservicios.
# 5. Compila solo el microservicio seleccionado.
# 6. Usa una imagen base ligera de OpenJDK 17 para ejecutar el microservicio.
# 7. Copia el JAR generado del microservicio seleccionado al contenedor final.
# 8. Define el comando de inicio para ejecutar el microservicio.
#
# Instrucciones de uso:
# - Construye la imagen para un microservicio específico (por ejemplo, ws-nomina):
#     docker build --build-arg MODULE=ws-nomina -t ws-nomina .
# - O para ws-trabajador:
#     docker build --build-arg MODULE=ws-trabajador -t ws-trabajador .
# -----------------------------------------------------------------------------

# Etapa de construcción: compila el microservicio seleccionado
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Permite seleccionar el módulo a compilar
ARG MODULE

# Copia los archivos pom.xml necesarios
COPY pom.xml .
COPY ws-nomina/pom.xml ws-nomina/
COPY ws-trabajador/pom.xml ws-trabajador/

# Descarga dependencias para aprovechar la cache de Docker
RUN mvn dependency:go-offline

# Copia el código fuente de los microservicios
COPY ws-nomina/ ws-nomina/
COPY ws-trabajador/ ws-trabajador/

# Compila solo el microservicio seleccionado
RUN mvn -pl ${MODULE} -am clean install -DskipTests

# Etapa final: imagen ligera solo con el JAR necesario
FROM eclipse-temurin:17-jre

WORKDIR /app

# Permite seleccionar el módulo a ejecutar
ARG MODULE

# Copia el JAR generado del microservicio seleccionado
COPY --from=build /app/${MODULE}/target/${MODULE}*.jar microservicio.jar

# Comando de ejecución del microservicio
ENTRYPOINT ["sh", "-c", "java -jar microservicio.jar"]