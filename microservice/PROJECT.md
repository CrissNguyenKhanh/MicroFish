# Project
Spring Boot microservice dau tay cho user management.

# Project dang lam gi?
- Expose REST API cho user.
- Luu user bang Spring Data JPA.
- Runtime dung MySQL local theo kieu XAMPP-friendly.
- Test profile dung H2 in-memory de khong phu thuoc MySQL.
- Uu tien chay duoc first microservice dau tien truoc khi them distributed-system tool.

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
- Da co actuator health endpoint.
- Da co `notes/dev-log.md`.
- Da co `README.md` va `scripts/smoke-test.ps1` cho first run.

# Task tiep theo
- Giu stack nhe cho first microservice; chi them tool phan tan khi can.
- Danh cho user chay thu: start XAMPP, run app, chay smoke test script.

# Local run
1. Start MySQL in XAMPP.
2. Tao database `microservice_db` trong phpMyAdmin.
3. Chay app bang `mvn spring-boot:run`.
4. Health check o `http://localhost:8080/actuator/health`.
