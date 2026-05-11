# Project
Lightweight microservice monorepo dau tay cho user management.

# Project dang lam gi?
- Expose REST API cho user.
- Luu user bang Spring Data JPA.
- Runtime dung MySQL local theo kieu XAMPP-friendly.
- Test profile dung H2 in-memory de khong phu thuoc MySQL.
- Uu tien chay duoc first microservice dau tien truoc khi them distributed-system tool.

# Cau truc hien tai
```text
microservice/
-> pom.xml                       parent Maven aggregator
-> services/user-service          Spring Boot service dang chay
-> gateway                        scaffold api-gateway sau nay
-> shared                         scaffold code chung sau nay
-> scripts                        script chay thu
-> notes                          dev log
```

# Kien truc user-service
```text
HTTP request
-> Controller
-> Service
-> Repository
-> Database
```

# Trang thai hien tai
- Da tach app vao `services/user-service`.
- Root `pom.xml` la Maven parent aggregator.
- Da co `User` entity, `UserRepository`, `UserService`.
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
- Da co `README.md` va `scripts/smoke-test.ps1` cho first run.
- `mvn test` tu root da pass 9 tests.

# Task tiep theo
- Danh cho user chay thu: start XAMPP, run service, chay smoke test script.
- Chi them gateway/service discovery/config server khi user-service don da chay on.

# Local run
1. Start MySQL trong XAMPP.
2. Tao database `microservice_db` trong phpMyAdmin.
3. Chay service bang `mvn -pl services/user-service spring-boot:run`.
4. Health check o `http://localhost:8080/actuator/health`.
