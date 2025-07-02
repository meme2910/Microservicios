# Microservicios

Este proyecto contiene el código del curso de microservicios el cual es para pruebas he impartición de curso. Incluye un par de microservicios independientes, cada uno con su propia documentación y funcionalidades, que pueden tener comunicación entre ellos para los test.

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
```
    microservicios/
    ├── ws-nomina/
    │   ├── src/
    │   ├── Dockerfile
    │   └── pom.xml
    ├── ws-trabajador/
    │   ├── src/
    │   ├── Dockerfile
    │   └── pom.xml
    ├── Dockerfile
    ├── Dockerfile.ws-trabajador
    ├── Dockerfile.ws-nomina
    ├── .dockerignore
    ├── pom.xml
    └── README.md
```
Cada microservicio es independiente, pero puede comunicarse con el otro si así se configura.

## Cómo ejecutar localmente (Java)

1. Clona el repositorio:
```sh
   git clone https://github.com/meme2910/Microservicios.git
   cd Microservicios
```
2. Entra a la carpeta de un microservicio (por ejemplo):
```sh
   cd ws-nomina
```
3. Ejecuta el microservicio con Maven:
```sh
   mvn spring-boot:run
```

## Cómo ejecutar con Docker

Este proyecto incluye varias opciones para construir y ejecutar los microservicios usando Docker. Puedes elegir entre construir imágenes individuales para cada microservicio o usar el Dockerfile multi-módulo parametrizable.

Para cada uno de estos se debera de clonar el repositorio
```sh
   git clone https://github.com/meme2910/Microservicios.git
   cd Microservicios
```

### Opción 1: Dockerfile específico por microservicio

Cada microservicio tiene su propio Dockerfile dentro de su carpeta:

- `ws-nomina/Dockerfile`
- `ws-trabajador/Dockerfile`

Estos Dockerfile están diseñados para ejecutar directamente el JAR ya generado.

> **Nota:** Antes de construir la imagen, asegúrate de haber ejecutado `mvn clean install` para generar el JAR.

**Ejemplo para ws-nomina:**
```sh
cd ws-nomina
mvn clean install
docker build -t ws-nomina .
docker run -p 8080:8080 ws-nomina
```

**Ejemplo para ws-trabajador:**
```sh
cd ws-trabajador
mvn clean install
docker build -t ws-trabajador .
docker run -p 8081:8080 ws-trabajador
```
*(Ajusta el puerto según la configuración de tu microservicio)*

### Opción 2: Dockerfile multi-módulo en la raíz

En la raíz del proyecto hay un `Dockerfile` que permite construir la imagen de cualquier microservicio usando un argumento de compilación (`MODULE`). Este método compila el microservicio seleccionado y genera una imagen lista para producción.

**Ejemplo:**
```sh
#Sustituir <microservicio> por el que se desea ejecutar Ejempplo: 'ws-nomina' o 'ws-trabajador'
docker build --build-arg MODULE=<microservicio> -t <microservicio> .
docker run -p 8080:8080 <microservicio>
```

> **Nota:** No es necesario ejecutar `mvn clean install` previamente, ya que el Dockerfile se encarga de compilar y empaquetar el microservicio seleccionado durante el proceso de construcción de la imagen.

### Opción 3: Dockerfile.ws-nomina y Dockerfile.ws-trabajador

También existen Dockerfile independientes en la raíz (`Dockerfile.ws-nomina` y `Dockerfile.ws-trabajador`) que permiten construir cada microservicio de forma explícita desde la raíz del proyecto.

**Ejemplo para ws-trabajador:**
```sh
docker build -f Dockerfile.ws-trabajador -t ws-trabajador .
docker run -p 8081:8080 ws-trabajador
```

**Ejemplo para ws-nomina:**
```sh
docker build -f Dockerfile.ws-nomina -t ws-nomina .
docker run -p 8080:8080 ws-nomina
```
> **Nota:** Tampoco es necesario ejecutar `mvn clean install` antes de construir la imagen con estos Dockerfile, ya que ellos mismos compilan y empaquetan el microservicio durante el proceso de construcción.

---

**Resumen de archivos Docker disponibles:**

- [`ws-nomina/Dockerfile`](ws-nomina/Dockerfile): Imagen ligera para ejecutar el JAR de ws-nomina.
- [`ws-trabajador/Dockerfile`](ws-trabajador/Dockerfile): Imagen ligera para ejecutar el JAR de ws-trabajador.
- [`Dockerfile`](Dockerfile): Multi-módulo, parametrizable por microservicio.
- [`Dockerfile.ws-nomina`](Dockerfile.ws-nomina): Construcción y empaquetado desde la raíz para ws-nomina.
- [`Dockerfile.ws-trabajador`](Dockerfile.ws-trabajador): Construcción y empaquetado desde la raíz para ws-trabajador.

> Consulta los comentarios al inicio de cada Dockerfile para más detalles sobre los pasos y recomendaciones de uso.


## Documentación Swagger

Una vez levantado el microservicio, accede a la documentación de la API en:
```
http://localhost:8080/swagger-ui.html
```
(Reemplaza el puerto si tu microservicio usa otro)

## Documentación H2

Una vez levantado el microservicio, se puede ingresar a la Base de Datos en memoria de H2
```
http://localhost:8080/h2-console
```
(Reemplaza el puerto si tu microservicio usa otro)

Los datos de la conexion a la BD se encuentran dentro de 
```
src/main/java/resources/application.properties
```
## Autor

- Manuel Antonio Ramirez Gomez (meme2910)
