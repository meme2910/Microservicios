# ---------------------------
# Dockerfile multi-módulo parametrizable por microservicio
# ---------------------------

FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Declarar el ARG dentro de la etapa
ARG MODULE

# Copia los pom.xml necesarios
COPY pom.xml .
COPY ws-nomina/pom.xml ws-nomina/
COPY ws-trabajador/pom.xml ws-trabajador/

# Descarga dependencias
RUN mvn dependency:go-offline

# Copia el resto del código fuente
COPY ws-nomina/ ws-nomina/
COPY ws-trabajador/ ws-trabajador/

# Usa el argumento de módulo para compilar solo ese microservicio
RUN mvn -pl ${MODULE} -am clean install -DskipTests

# ---------------------------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Declarar el ARG dentro de la etapa
ARG MODULE

# Usa el argumento de módulo para copiar el JAR correcto
COPY --from=build /app/${MODULE}/target/${MODULE}*.jar ${MODULE}.jar

# Usa el nombre del módulo para ejecutar el JAR correspondiente
ENTRYPOINT ["sh", "-c", "java -jar ${MODULE}.jar"]

# ---------------------------
# Uso:
# Construye la imagen para un microservicio específico (por ejemplo, ws-nomina):
#   docker build --build-arg MODULE=ws-nomina -t ws-nomina .
# O para ws-trabajador:
#   docker build --build-arg MODULE=ws-trabajador -t ws-trabajador .