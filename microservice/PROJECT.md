# Project
Lightweight microservice monorepo dau tay cho user management.

# Project dang lam gi?
- Gateway lam front door tren port 8080.
- User-service lam CRUD user tren port 8081.
- User-service luu user bang Spring Data JPA.
- Runtime dung MySQL local theo kieu XAMPP-friendly.
- Test profile dung H2 in-memory de khong phu thuoc MySQL.
- Uu tien chay duoc local microservice flow truoc khi them distributed-system tool.

# Cau truc hien tai
```text
microservice/
-> pom.xml                       parent Maven aggregator
-> gateway                       Spring Boot gateway nhe
-> services/user-service          Spring Boot user service
-> shared                         scaffold code chung sau nay
-> scripts                        script chay thu
-> notes                          dev log
```

# Kien truc hien tai
```text
Client
-> Gateway :8080
-> User-service :8081
-> Controller
-> Service
-> Repository
-> MySQL
```

# Trang thai hien tai
- Da co gateway module.
- Gateway route:
  - `/users/**` -> user-service
  - `/hello/**` -> user-service
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
- Da co actuator health endpoint cho gateway va user-service.
- Da co `scripts/smoke-test.ps1` cho first run qua gateway.
- `mvn test` tu root da pass 10 tests.

# Task tiep theo
- User chay thu local flow: MySQL -> user-service -> gateway -> smoke test.
- Sau khi flow on, tao `shared` module that cho common response/error neu can.
- Chi them Eureka/Config Server khi da co tu 2 service nghiep vu tro len.

# Local run
1. Start MySQL trong XAMPP.
2. Tao database `microservice_db` trong phpMyAdmin.
3. Terminal 1: chay user-service bang `mvn -pl services/user-service spring-boot:run`.
4. Terminal 2: chay gateway bang `mvn -pl gateway spring-boot:run`.
5. Gateway health check o `http://localhost:8080/actuator/health`.
6. User-service health check o `http://localhost:8081/actuator/health`.
7. Smoke test qua gateway bang `.\scripts\smoke-test.ps1`.
