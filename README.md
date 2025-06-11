# Curso FYG Microservicios

Este proyecto contiene el código del curso de microservicios. Incluye dos microservicios independientes, cada uno con su propia documentación y funcionalidades.

## Tecnologías utilizadas

- Java 17+
- Spring Boot
- Maven

### Dependencias incluidas

- H2 Database (base de datos en memoria)
- Swagger/OpenAPI (documentación de APIs)
- Lombok (reducción de código repetitivo)
- Spring Data JPA (acceso a datos)
- Spring Web
- Docker (empaquetado y despliegue de microservicios)

## Estructura del proyecto

curso-fyg-microservicios/
├── ws-nomina/
│   ├── src/
│   └── pom.xml
├── ws-trabajador/
│   ├── src/
│   └── pom.xml
└── README.md

Cada microservicio es independiente, pero puede comunicarse con el otro si así se configura.

## Cómo ejecutar localmente (Java)

1. Clona el repositorio:
   git clone https://github.com/tu-usuario/Curso-FYG-Microservicios.git
   cd Curso-FYG-Microservicios

2. Entra a la carpeta de un microservicio (por ejemplo):
   cd ws-nomina

3. Ejecuta el microservicio con Maven:
   mvn spring-boot:run

## Cómo ejecutar con Docker

1. Clona el repositorio:
   git clone https://github.com/tu-usuario/Curso-FYG-Microservicios.git
   cd Curso-FYG-Microservicios

2. Entra a la carpeta de un microservicio (por ejemplo):
   cd ws-nomina

3. Construir la imagen del Dockerfile:
    docker build -t ws-nomina .
    docker run -p 8080:8080 ws-nomina

## Documentación Swagger

Una vez levantado el microservicio, accede a la documentación de la API en:

http://localhost:8080/swagger-ui.html

(Reemplaza el puerto si tu microservicio usa otro)

## Documentación H2

Una vez levantado el microservicio, se puede ingresar a la Base de Datos en memoria de H2

http://localhost:8080/h2-console

(Reemplaza el puerto si tu microservicio usa otro)

Los datos de la conexion a la BD se encuentran dentro de 

src/main/java/resources/application.propertiesq

## Autor

- Manuel Antonio Ramirez Gomez (meme2910)
