## Índice
1. [Introducción](#introducción)
2. [Solución propuesta](#Solución-propuesta)
3. [Instalación y Configuración](#instalación-y-configuración)
5. [Diagrama de Secuencia](#diagrama-de-secuencia)
6. [Diagrama de Componentes](#diagrama-de-componentes)
[input.md](..%2F..%2FDownloads%2F%5BUpdated%20Octubre%202023%5D%20Ejercicio%20%231%20-%20BCI%20%283%29.md%2Finput.md)

## Introducción
El siguiente proyecto se desarrollo con el fin de postular a la posición a Java Dev Senior que mantiene actualmente la empresa GlobalLogic. Para poder evaluar,
se debe generar un test de código que consiste en crear un microservicio en Spring Boot que  gestione usuarios.

## Requisitos

Este proyecto debe consistir en el desarrollo de un microservicio utilizando **Spring Boot 2.5.14** y **Gradle** (hasta la versión 7.4) para la creación y consulta de usuarios. Este README contiene instrucciones para la construcción, ejecución y documentación del proyecto.

### Tecnologías y Requisitos

- **Java 8 u 11** (Utilización de al menos dos características de estas versiones).
- **Spring Boot 2.5.14**
- **Gradle** hasta la versión 7.4
- **Spring Data** para persistencia de datos en **H2 Database**.
- **JWT** para generación de tokens.
- **Spock Framework** o **JUnit** para pruebas unitarias con una cobertura mínima de 80% en el servicio.

### Endpoints

#### 1. `/sign-up` - Creación de Usuario

- **Método:** `POST`
- **Descripción:** Permite crear un nuevo usuario.
- **Formato de Entrada:**
  ```json
  {
      "name": "String",
      "email": "String",
      "password": "String",
      "phones": [
          {
              "number": long,
              "citycode": int,
              "contrycode": "String"
          }
      ]
  }
  ```

### Requisitos de Validación y Persistencia de Usuario

1. **Validación de Correo Electrónico:**
    - El correo debe seguir una expresión regular para validar que su formato sea el correcto (ejemplo: `aaaaaaa@undominio.algo`).
    - Si no cumple con el formato adecuado, se debe enviar un mensaje de error.

2. **Validación de Clave:**
    - La clave debe seguir una expresión regular para validar que su formato sea el correcto.
    - Debe contener solo una letra mayúscula y exactamente dos números (no necesariamente consecutivos).
    - La combinación debe incluir letras minúsculas.
    - El largo máximo de la clave debe ser de 12 caracteres y el mínimo de 8 caracteres.
    - Ejemplo: `"a2asfGfdfdf4"`.
    - Si no cumple con el formato adecuado, se debe enviar un mensaje de error.

3. **Campos Opcionales:**
    - El nombre y los teléfonos son campos opcionales.

4. **Datos a Retornar en Caso de Éxito:**
    - `id`: ID del usuario (puede ser generado por el banco de datos, pero sería más deseable un UUID).
    - `created`: Fecha de creación del usuario.
    - `lastLogin`: Fecha del último ingreso.
    - `token`: Token de acceso de la API (debe utilizar JWT).
    - `isActive`: Indica si el usuario sigue habilitado dentro del sistema.

5. **Persistencia de Usuario:**
    - El usuario debe ser persistido en una base de datos utilizando Spring Data (se recomienda usar H2).
    - En el caso de la contraseña, sería ideal que se pudiera encriptar.

6. **Manejo de Usuarios Existentes:**
    - Si el usuario ya existe en la base de datos, se debe enviar un mensaje de error indicando que el usuario ya existe.

- **En caso de error de un endpoint debe retornar:** 

  ```json
  {
    "error": [{
      "timestamp": "Timestamp",
      "codigo": "int",
      "detail": "String"
    }]
  }

#### Endpoint Obligatorio: `/login`

Este endpoint será utilizado para consultar el usuario. Para ello, debe utilizar el token generado en el endpoint anterior para realizar la consulta y retornar toda la información del usuario persistido. Tenga en cuenta que el token debe cambiar al ejecutar el endpoint, por lo que se actualizará el token.

#### Contrato de salida:
  ```json
  {
    "id": "e5c6cf84-8860-4c00-91cd-22d3be28904e",
    "created": "Nov 16, 2021 12:51:43 PM",
    "lastLogin": "Nov 16, 2021 12:51:43 PM",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpb0B0ZXN0...",
    "isActive": true,
    "name": "Julio Gonzalez",
    "email": "julio@testssw.cl",
    "password": "a2asfGfdfdf4",
    "phones": [
      {
        "number": 87650009,
        "citycode": 7,
        "contrycode": "25"
      }
    ]
  }
```
 

## Solución propuesta
Se genera un microservicio llamado user-manager-side el cual contiene varios aspectos a conciderar:
1. Se crea en base a un patron arquitectonico llamado CQRS(Command and Query Segregation) que separa las 
responsabilidades de creacion(commands) de las de lecturas(querys). 
Para mas detalles consultar las clases UserCmdApi y UserQueryApi 
2. Esta creado pensando principalmente en el dominio utilizando DDD(Domanin driven design) por aquello
se encontraran conceptos como agregados, objetos de valor, repositorios etc.
3. El modelo esta construido sobre lo que expone el libro 
"The Data Model Resource Book, Vol. 1: A Library of Universal Data Models for All Enterprises"
el cual toma en concideracion que los usuarios son participantes de un sistema. Este modelo es mas complejo
pero mas estandar y por conciguiente no se realiza Spring Data(Jpa - Hibernate) sino con MyBatis que 
permite un mejor control sobre las consultas y las transacciones.
4. Se aplican algunos tipos de datos funcionales como Try e Either que permiten manipular mejor los 
resultados y las excepciones en los metodos conciguierdo una mejor semantica en el codigo. Para mas info puedes
visitar la pagina de la lib https://vavr.io/
5. Se implementa una arquitectura hexagonal en el microservicio diferenciando claramente las capa de dominio,
de la capa de aplicacion y la de infraestructura.
6. La solucion propuesta contempla la autentificacion del usuario y la encriptacion de la contraseña y esto le da
un plus ya que era un deseable que se concreto :-)

   

## Instalación y Configuración
1. Clona el repositorio desde GitHub
```sh
git clone https://github.com/jdanieldeveloper/tecnical-test-global-logic.git
```
2. Navega al directorio del proyecto.
```sh
cd user-manager-side
```
3. Verifica si tienes java version 11 en tu Pc
```sh
java --version
```
3. Construye el proyecto con Gradle.
```sh
./gradlew build
```

4. Ejecuta el proyecto.
```sh
./gradlew bootRun
```

5. Para testear el los endpoints se utilizo posman se pueden importar los siguientes scripts:
- Endpoint de creacion de usuario:
```sh
curl --location 'http://127.0.0.1:8083/user-manager-side/api/v1/command/users/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Daniel Carvajal",
    "email": "dcarvajal1@gmail.com",
    "password": "m1Passw2rd",
    "phones": [
        {
            "countryCode": "56",
            "cityCode": 9,
            "number": 12345678
        }
    ]
}'
```

- Endpoint de login de usuario:
```sh
curl --location 'http://127.0.0.1:8083/user-manager-side/api/v1/query/users/login' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkY2FydmFqYWwxQGdtYWlsLmNvbSIsIlJPTEVfVklTSVRPUl9DUkVBVEUiOiJST0xFX1ZJU0lUT1JfQ1JFQVRFIiwiUk9MRV9WSVNJVE9SX1JFQUQiOiJST0xFX1ZJU0lUT1JfUkVBRCIsImV4cCI6MTczMDk1NTE0OSwiaWF0IjoxNzMwOTE5MTQ5fQ.z12LXSLt-2gDjVWPPiUH5bBiD-p7EiBTmMnt9w9iqSk' \
--data-raw '{  
  "email": "dcarvajal1@gmail.com",
  "password": "m1Passw2rd"  
}'
```
 
Nota: Si deseas ejecutar los test y verificar la covertura del codigo +80% ejecuta los siguientes comandos:
```sh
cd user-manager-side
./gradlew test
./gradlew jacocoTestReport
```
Luego puedes ir a la carpeta de reportes y ejecutar en un navegador el index.html
```sh
cd build/reports/jacoco/test/html
```

## Diagrama de Secuencia
Los diagramas se realizaron con el software draw.io(https://app.diagrams.net/) puede dirigir a la carpeta donde 
estaran los fuentes o visualizar el diagrama en formato png

![Diagrama del sistema](docs/user-manager-seq-diagram.png)



## Diagrama de Componentes
Por definir 






