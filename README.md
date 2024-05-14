# PersonApp Hexa Spring Boot

Esta es una plantilla para el Laboratorio de Arquitectura Limpia en Spring Boot.

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

1. Iniciar la aplicación Spring Boot.
2. Acceder a Swagger en [http://localhost:3000/swagger-ui.html](http://localhost:3000/swagger-ui.html).

## Endpoints

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

---

Claro, puedo ayudarte a mejorar tu README. Aquí te dejo una versión más organizada y detallada:

---

