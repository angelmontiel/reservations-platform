# Reservations Service

API REST para la gestión de citas de negocios locales Los negocios pueden administrar sus servicios, empleados y horarios; los clientes pueden reservar citas evitando dobles reservas.

## 🚧 Estado del proyecto

En desarrollo activo — Fase 1 (monolito funcional).

- [x] Entidad `Business` + CRUD (crear)
- [ ] CRUD completo de `Business` (listar, actualizar, eliminar)
- [ ] Entidades `Service`, `Employee`, `Schedule`, `Customer`
- [ ] Lógica de disponibilidad y citas (`Appointment`)
- [ ] Pagos (`Payment`) e integración con Stripe
- [ ] Autenticación con JWT
- [ ] Migración a microservicios
- [ ] CI/CD y despliegue

## 🛠️ Stack tecnológico

| Área | Tecnología |
|---|---|
| Framework | Spring Boot 4 |
| Base de datos | PostgreSQL |
| ORM | Hibernate / Spring Data JPA |
| Validación | Bean Validation (Jakarta) |
| Documentación API | springdoc-openapi (Swagger UI) |
| Build tool | Maven |

## 📂 Estructura del proyecto

src/main/java/com/kukultec/reservations_service/
├── entity/ # Entidades JPA
├── dto/
│ ├── request/ # DTOs de entrada (por entidad)
│ └── response/ # DTOs de salida (por entidad)
├── mapper/ # Conversión entidad <-> DTO
├── repository/ # Interfaces JpaRepository
├── service/ # Lógica de negocio
├── controller/ # Endpoints REST
└── exception/ # Excepciones propias y manejo global de errores


## ▶️ Cómo levantarlo localmente

### Requisitos
- Java 21+
- Maven
- PostgreSQL corriendo en `localhost:5432`

### Pasos

1. Clona el repositorio:
```bash
   git clone https://github.com/angelmontiel/reservations-service.git
   cd reservations-service
```

2. Crea la base de datos en PostgreSQL:
```sql
   CREATE DATABASE booking_platform_db;
```

3. Configura tus credenciales en `src/main/resources/application-dev.properties` (ver `application-dev.properties.example` si existe, o ajusta usuario/contraseña de tu PostgreSQL local).

4. Levanta la aplicación:
```bash
   ./mvnw spring-boot:run
```

5. La API estará disponible en `http://localhost:8081`, y la documentación Swagger en `http://localhost:8081/swagger-ui.html`.

## 📖 Documentación de la API

Una vez levantado el proyecto, la documentación interactiva está en:

http://localhost:8081/swagger-ui.html


## 📝 Licencia

Este proyecto es de uso personal/educativo.