# Topsecret API

Esta es una API REST construida con Spring Boot que permite calcular la posición y el mensaje decodificado proveniente de una red de satélites.

## Requisitos

- **Java 17 o superior** (recomendado Java 17). Asegúrate de tener `JAVA_HOME` configurado.
- **Maven**. En este proyecto se incluye el *wrapper* (`mvnw`), por lo que no es necesario tener Maven instalado previamente.

## Ejecución en local

1. Clona este repositorio y sitúate en la raíz del proyecto.

2. Compila y ejecuta la aplicación con:

   ```bash
   ./mvnw spring-boot:run
   ```

   Si prefieres generar el `jar`, puedes ejecutar:

   ```bash
   ./mvnw clean package
   java -jar target/topsecret-0.0.1-SNAPSHOT.jar
   ```

La API estará disponible en `http://localhost:8080`.

## Endpoints principales

- `POST /topsecret`

  Recibe un cuerpo con la lista de satélites y devuelve la posición y el mensaje descifrado.

- `POST /topsecret/topsecret_split/{satellite_name}`

  Permite registrar la información de un satélite individualmente.

- `GET /topsecret/topsecret_split`

  Obtiene la posición y el mensaje una vez que se ha recibido información de al menos tres satélites.
  ## Pruebas

Puedes ejecutar las pruebas con:

```bash
./mvnw test
```

