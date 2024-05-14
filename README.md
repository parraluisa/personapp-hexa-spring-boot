# PersonApp Hexa Spring Boot
## Sistema de Gestión de Personas

Este proyecto es un sistema de gestión de personas que te permite administrar información sobre personas, teléfonos, profesiones y estudios.

## Integrantes

- Erick Santiago Garavito Villamil
- Fabio Luis Buitrago Ochoa
- Luisa Lorena Parra Nivia

## Requisitos previos

- MariaDB en el puerto 3307
- MongoDB en el puerto 27017

## Instrucciones de instalación

1. Ejecutar los scripts en las bases de datos
2. Configurar Lombok en sus IDEs

## Ejecución

- El adaptador REST corre en el puerto 3000
- Swagger disponible en [http://localhost:3000/swagger-ui.html](http://localhost:3000/swagger-ui.html)

## Advertencia

Por favor, no editar este repositorio directamente. Puedes hacer Fork a este repo y trabajar en tu propia copia.

## Por aprender

- Java
- Spring Boot
- MongoDB
- MariaDB
- Swagger

## Instalación

1. Instalar Java, Spring Boot, MongoDB y MariaDB según las instrucciones de cada plataforma.
2. Ejecutar los scripts en las bases de datos.
3. Configurar Lombok en sus IDEs.

## Ejecución

### CLI

Para ejecutar la aplicación en modo CLI, sigue estos pasos:

1. Asegúrate de tener Java instalado en tu sistema.
2. Ejecuta el archivo `PersonaAppCli.java` para ingresar al menú.
3. Sigue las instrucciones en pantalla para interactuar con la aplicación.

### Swagger

Para ejecutar la API en Swagger, sigue estos pasos:

1. Asegúrate de tener Java instalado en tu sistema.
2. Ejecuta el archivo `PersonaAppRestApi.java` para iniciar la API.
3. Abre un navegador web y navega a `http://localhost:PUERTO/swagger-ui.html` (reemplaza `PUERTO` con el puerto en el que se está ejecutando la API) para acceder a la documentación de la API en Swagger.

## Opciones del Menú CLI

### Persona

- **Ver todas las personas:** Muestra una lista de todas las personas registradas en el sistema.
- **Crear una persona:** Permite crear un nuevo registro de persona, ingresando su nombre, apellido, género y edad.
- **Actualizar una persona:** Permite actualizar la información de una persona existente, buscándola por su identificación.
- **Buscar una persona:** Busca una persona por su identificación y muestra su información.
- **Eliminar una persona:** Elimina una persona del sistema, buscándola por su identificación.

### Teléfono

- **Ver todos los teléfonos:** Muestra una lista de todos los teléfonos registrados en el sistema.
- **Crear un teléfono:** Permite crear un nuevo registro de teléfono, ingresando el número, la compañía y el id de la persona a la que pertenece.
- **Actualizar un teléfono:** Permite actualizar la información de un teléfono existente, buscándolo por su número.
- **Buscar un teléfono:** Busca un teléfono por su número y muestra su información.
- **Eliminar un teléfono:** Elimina un teléfono del sistema, buscándolo por su número.

### Profesión

- **Ver todas las profesiones:** Muestra una lista de todas las profesiones registradas en el sistema.
- **Crear una profesión:** Permite crear un nuevo registro de profesión, ingresando su nombre y descripción.
- **Actualizar una profesión:** Permite actualizar la información de una profesión existente, buscándola por su identificación.
- **Buscar una profesión:** Busca una profesión por su identificación y muestra su información.
- **Eliminar una profesión:** Elimina una profesión del sistema, buscándola por su identificación.

### Estudio

- **Ver todos los estudios:** Muestra una lista de todos los estudios registrados en el sistema.
- **Crear un estudio:** Permite crear un nuevo registro de estudio, ingresando su nombre y nivel.
- **Actualizar un estudio:** Permite actualizar la información de un estudio existente, buscándolo por su identificación.
- **Buscar un estudio:** Busca un estudio por su identificación y muestra su información.
- **Eliminar un estudio:** Elimina un estudio del sistema, buscándolo por su identificación.

## Endpoints Swagger

### Estudios

- **GET /api/v1/estudio/{database}**: Obtiene todos los estudios de la base de datos especificada.
- **POST /api/v1/estudio**: Crea un nuevo estudio.

### Personas

- **GET /api/v1/persona/{database}**: Obtiene todas las personas de la base de datos especificada.
- **POST /api/v1/persona**: Crea una nueva persona.
- **PUT /api/v1/persona**: Actualiza la información de una persona.
- **DELETE /api/v1/persona/{database}/{identification}**: Elimina una persona por su identificación.
- **GET /api/v1/persona/{database}/{identification}**: Busca una persona por su identificación.

### Profesiones

- **GET /api/v1/profesion/{database}**: Obtiene todas las profesiones de la base de datos especificada.
- **POST /api/v1/profesion**: Crea una nueva profesión.
- **PUT /api/v1/profesion**: Actualiza la información de una profesión.
- **GET /api/v1/profesion/{database}/{id}**: Busca una profesión por su ID.
- **DELETE /api/v1/profesion/{database}/{id}**: Elimina una profesión por su ID.

### Teléfonos

- **GET /api/v1/telefono/{database}**: Obtiene todos los teléfonos de la base de datos especificada.
- **POST /api/v1/telefono**: Crea un nuevo teléfono.
- **PUT /api/v1/telefono**: Actualiza la información de un teléfono.
- **GET /api/v1/telefono/{database}/{id}**: Busca un teléfono por su ID.
- **DELETE /api/v1/telefono/{database}/{id}**: Elimina un teléfono por su ID.


