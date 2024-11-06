# tecnical-test-global-logic

## Introducción


## Índice
1. [Introducción](#introducción)
2. [Requisitos del Sistema](#requisitos-del-sistema)
3. [Instalación y Configuración](#instalación-y-configuración)
4. [Patrones de Diseño](#patrones-de-diseño)
5. [Diagrama de Secuencia](#diagrama-de-secuencia)
6. [Diagrama de Componentes](#diagrama-de-componentes)
7. [Pruebas Unitarias](#pruebas-unitarias)
8. [Conclusiones](#conclusiones)

## Requisitos del Sistema

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
    - `codigo`: Código de error HTTP.
    - `detail`: Descripción del error.

## Instalación y Configuración
1. Clona el repositorio desde GitHub, GitLab o Bitbucket.


2. Navega al directorio del proyecto.


3. Construye el proyecto con Gradle.


4. Ejecuta el proyecto.


## Patrones de Diseño


### Patrón Factory


## Diagrama de Secuencia


## Diagrama de Componentes


Para ejecutar las pruebas:


## Conluciones
