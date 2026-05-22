# Pruebas paso a paso (Lógica de Negocio) — Sistema de Inscripcion a Cursos

Este documento explica cómo probar, de forma manual y paso a paso (para principiantes), toda la lógica de negocio implementada en el servicio de **Inscripciones**.

## 1 Requisitos antes de empezar

### 1.1 Tener la API corriendo

El proyecto está configurado en `src/main/resources/application.properties` para conectarse a PostgreSQL usando variables de entorno:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

Ejemplo de valores típicos:

- `DB_URL=jdbc:postgresql://localhost:5432/demo_basic`
- `DB_USERNAME=postgres`
- `DB_PASSWORD=postgres`

Luego inicia la aplicación (desde la raíz del proyecto):

```bash
.\mvnw.cmd spring-boot:run
```

Cuando esté arriba, abre Swagger:

- `http://localhost:8080/swagger-ui.html`
- `http://localhost:8080/api-docs`

##  Validación de la Logica del Negocio

### Creación de la base de datos

*Usuarios*

```json
{
  "nombre": "Ana",
  "apellido": "Ana",
  "genero": "Femenino",
  "edad": 20,
  "email": "ana@prueba.com",
  "password": "123",
  "rol": "ADMIN",
  "ubicacion": {
    "pais": "Colombia",
    "departamento": "Antioquia",
    "ciudad": "Medellín"
  }
}

{
  "nombre": "Jose",
  "apellido": "Jose",
  "genero": "Masculino",
  "edad": 35,
  "email": "jose@prueba.com",
  "password": "456",
  "rol": "JUGADOR",
  "ubicacion": {
    "pais": "Colombia",
    "departamento": "Cundinamarca",
    "ciudad": "Bogotá"
  }
}

{
  "nombre": "Ivan",
  "apellido": "Ivan",
  "genero": "Masculino",
  "edad": 15,
  "email": "ivan@prueba.com",
  "password": "789",
  "rol": "JUGADOR",
  "ubicacion": {
    "pais": "Colombia",
    "departamento": "Nariño",
    "ciudad": "Pasto"
  }
}

{
  "nombre": "Sofi",
  "apellido": "Sofi",
  "genero": "Femenino",
  "edad": 25,
  "email": "sofi@prueba.com",
  "password": "321",
  "rol": "ADMIN",
  "ubicacion": {
    "pais": "Colombia",
    "departamento": "Boyacá",
    "ciudad": "Tunja"
  }
}
```
*Cursos*

```json
{
  "nombreCurso": "Fundamentos de Java",
  "descripcionCurso": "Aprende las bases de la programación orientada a objetos.",
  "numeroNiveles": 5,
  "categoria": "PROGRAMACION",
  "dificultad": "BASICO"
}

{
  "nombreCurso": "Spring Boot Avanzado",
  "descripcionCurso": "Domina la creación de APIs profesionales.",
  "numeroNiveles": 8,
  "categoria": "PROGRAMACION",
  "dificultad": "INTERMEDIO"
}

{
  "nombreCurso": "Arquitectura de Microservicios con Spring Cloud",
  "descripcionCurso": "Aprende a diseñar sistemas escalables, tolerantes a fallos y distribuidos utilizando patrones avanzados.",
  "numeroNiveles": 10,
  "categoria": "PROGRAMACION",
  "dificultad": "AVANZADO"
}

{
  "nombreCurso": "Presente Perfecto",
  "descripcionCurso": "Aprende a utilizar el presente perfecto.",
  "numeroNiveles": 3,
  "categoria": "INGLES",
  "dificultad": "INTERMEDIO"
}

{
  "nombreCurso": "Hábitos de Estudio",
  "descripcionCurso": "Reto de 20 días de hábitos nocturnos .",
  "numeroNiveles": 2,
  "categoria": "HABITOS_ESTUDIO",
  "dificultad": "BASICO"
}
```