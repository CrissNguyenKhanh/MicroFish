# TODO

## Dang lam
- [x] Tao workflow docs cho project.
- [x] Hoan thien full CRUD user microservice.

## Tiep theo
- [x] Rename folder `src/main/java/com/example/microservice/Controller` thanh `src/main/java/com/example/microservice/controller`.
- [x] Them integration test cho `POST /users`.
- [x] Them integration test cho `GET /users/{id}`.
- [x] Them integration test cho `GET /users`.
- [x] Them integration test cho `PUT /users/{id}`.
- [x] Them integration test cho `DELETE /users/{id}`.
- [ ] Chot local database workflow: MySQL local hoac Docker Compose.
- [ ] Them Spring Boot Actuator health endpoint.
- [ ] Them Docker Compose cho MySQL.
- [ ] Can nhac bo Config/Eureka/Feign dependency neu chua dung trong service don.

## Phat hien, chua sua
- `application.yaml` runtime dang dung MySQL local voi password rong. Neu MySQL may khac config, app se fail khi start.
- Test log co warning `spring.jpa.open-in-view is enabled by default`. Nen set `spring.jpa.open-in-view=false`.
- Test log van khoi tao mot so bean Spring Cloud/Eureka du da disable discovery. Nen don dependency sau khi chot kien truc nhieu service.
