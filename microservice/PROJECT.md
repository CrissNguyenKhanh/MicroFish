# Project
Spring Boot microservice dau tay cho user management.

# Project dang lam gi?
- Expose REST API cho user.
- Luu user bang Spring Data JPA.
- Runtime dung MySQL local.
- Test profile dung H2 in-memory de khong phu thuoc MySQL.

# Kien truc lien quan
```text
HTTP request
-> Controller
-> Service
-> Repository
-> Database
```

# Trang thai hien tai
- Da co `User` entity.
- Da co `UserRepository`.
- Da co `UserService` voi full CRUD user.
- Da co REST API:
  - `POST /users`
  - `GET /users`
  - `GET /users/{id}`
  - `PUT /users/{id}`
  - `DELETE /users/{id}`
- Da co validation request bang Jakarta Validation.
- Da co global exception handler tra JSON loi sach.
- Da co integration tests bang MockMvc va H2.
- Da co `notes/dev-log.md`.

# Task tiep theo
- Chot cach chay local database: MySQL cai may hay Docker Compose.
- Them Actuator health endpoint cho service observability.
- Them Docker Compose cho MySQL.
