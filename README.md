
¡Bienvenido al backend de **DragonByte**! Esta es una API Rest robusta desarrollada con **Spring Boot** para gestionar una plataforma de educación en línea. El sistema cuenta con una arquitectura multicapa que administra de forma segura a los usuarios, sus mascotas virtuales, el catálogo de cursos y el proceso de inscripción.

---

##  Arquitectura del Proyecto

El backend está estructurado siguiendo las mejores prácticas de diseño de software en Java, dividido en paquetes según su responsabilidad:



* **`controller`**: Puerta de entrada de la API. Recibe las peticiones HTTP del frontend (React) y gestiona los códigos de respuesta (200, 201, 204, 404).
* **`service`**: Capa de negocio. Contiene la lógica, cálculos y restricciones del sistema.
* **`repository`**: Capa de persistencia. Interfaces que extienden de `JpaRepository` para comunicarse con la base de datos sin escribir SQL manual.
* **`model.entity`**: Moldes u objetos mapeados directamente a las tablas de la base de datos.
* **`model.embeddable`**: Componentes reutilizables que se incrustan dentro de las entidades (como direcciones o ubicaciones).
* **`model.enums`**: Listados de opciones fijas y constantes para estandarizar los datos.

---

## Estructura de la Base de Datos y Modelos

###  Entidades de Dominio (`model.entity`)
* **`BaseEntity`**: Clase abstracta heredable que provee auditoría básica (ID único, fechas de creación) a todas las demás entidades.
* **`Usuario`**: Almacena los datos de los estudiantes y administradores (nombre, correo, rol).
* **`Mascota`**: **¡Elemento de Gamificación!** Registra el compañero virtual asignado a cada usuario para incentivar el progreso del estudio.
* **`Curso`**: Contiene la información de los cursos (nombre, descripción, niveles, dificultad).
* **`Inscripcion`**: Tabla intermedia que conecta a un `Usuario` con un `Curso`, gestionando su avance.

###  Componentes Incrustables (`model.embeddable`)
* **`UbicacionUsuario`**: Agrupa campos de localización (país, ciudad) integrados directamente en la tabla de usuarios.

###  Enumeraciones (`model.enums`)
Para asegurar la integridad de los datos, se utilizan los siguientes Enums:
* `RolUsuario`: `ESTUDIANTE`, `PROFESOR`, `ADMINISTRADOR`.
* `EstadoInscripcion`: `ACTIVO`, `COMPLETADO`, `CANCELADO`.
* `CategoriaCurso`: `PROGRAMACION`, `INGLES`, `HABITOS`.
* `DificultadCurso`: `PRINCIPIANTE`, `INTERMEDIO`, `AVANZADO`.

---

##  Resumen de Endpoints Disponibles

La URL base local es: `http://localhost:8080`

###  Usuarios (`/api/usuarios`)
* `GET /api/usuarios` - Listar todos los usuarios de la plataforma.
* `GET /api/usuarios/{id}` - Obtener el perfil de un usuario por ID.
* `POST /api/usuarios` - Registrar un nuevo usuario (`UsuarioRequestDTO`).
* `PUT /api/usuarios/{id}` - Actualizar datos del usuario.
* `DELETE /api/usuarios/{id}` - Eliminar una cuenta.

###  Cursos (`/api/cursos`)
* `GET /api/cursos` - Listar catálogo de cursos completo.
* `GET /api/cursos/{id}` - Obtener detalles de un curso.
* `POST /api/cursos` - Crear un nuevo curso (`CursoRequestDTO`).
* `PUT /api/cursos/{id}` - Modificar información de un curso.
* `DELETE /api/cursos/{id}` - Eliminar un curso.

###  Inscripciones (`/api/inscripciones`)
* `GET /api/inscripciones` - Ver historial global de inscripciones.
* `POST /api/inscripciones` - Inscribir un alumno a un curso.
* `DELETE /api/inscripciones/{id}` - Cancelar una inscripción.
* `POST /api/inscripciones/{id}/completar` - Acción especial para marcar un curso como finalizado con éxito.

---

##  Sistema de Control de Errores Global

El proyecto implementa un `@RestControllerAdvice`, `GlobalExceptionHandler` que captura excepciones en tiempo de ejecución de manera centralizada. Esto garantiza que ante cualquier fallo como buscar un ID inexistente o enviar datos inválidos, el frontend reciba una respuesta estandarizada:

