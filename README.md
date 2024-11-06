## Índice
1. [Introducción](#introducción)
2. [Requisitos del Sistema](#requisitos-del-sistema)
3. [Instalación y Configuración](#instalación-y-configuración)
4. [Patrones de Diseño](#patrones-de-diseño)
5. [Diagrama de Secuencia](#diagrama-de-secuencia)
6. [Diagrama de Componentes](#diagrama-de-componentes)
7. [Pruebas Unitarias](#pruebas-unitarias)
8. [Conclusiones](#conclusiones)

## Introducción
El siguiente proyecto se desarrollo con el fin de postular a la posición a Java Dev Senior que mantiene actualmente la empresa GlobalLogic. Para poder evaluar,
se debe generar un test de código que consiste en crear un microservicio en Spring Boot que  gestione usuarios.

## Requisitos

- **Lenguaje y Entorno**: El microservicio está desarrollado en **Java** (versiones compatibles: 8 u 11), y debe utilizar al menos dos características específicas de estas versiones.

- **Framework y Herramientas de Construcción**:
    - **Spring Boot**: Versión 2.5.14.
    - **Gradle**: Hasta la versión 7.4, utilizado para la gestión y compilación del proyecto.

- **Pruebas Unitarias**: Se requiere un mínimo del **80% de cobertura en pruebas unitarias** sobre las funcionalidades del servicio, utilizando **Spock** o **JUnit** como frameworks de pruebas para asegurar la calidad y confiabilidad del código.

- **Endpoints**:
    - **/sign-up**: Endpoint para la creación de usuarios con validaciones específicas para el formato de email y contraseña. En caso de éxito, devuelve la información del usuario, incluyendo:
        - `id`: Identificador único (preferentemente un UUID).
        - `created`: Fecha de creación del usuario.
        - `lastLogin`: Última fecha de inicio de sesión.
        - `token`: Token de acceso JWT.
        - `isActive`: Estado de habilitación del usuario en el sistema.
    - **/login**: Endpoint para consultar un usuario mediante el token JWT. El token se actualiza en cada solicitud exitosa.

- **Formato de Datos**: Todos los endpoints deben aceptar y devolver datos en formato JSON, incluyendo los mensajes de error con el código HTTP correspondiente.

- **Base de Datos**: Utiliza una base de datos **H2** para la persistencia de datos de usuario, gestionada con Spring Data. Es recomendable encriptar las contraseñas antes de almacenarlas.
  
- **Manejo de Errores**: En caso de error, el sistema debe devolver un JSON con:
    - `timestamp`: Marca de tiempo del error.
    - `codigo`: Código de error.
    - `detail`: Descripción del error.

- ## Solución propuesta
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






