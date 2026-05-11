# TODO

## Da xong
- [x] Tao workflow docs cho project.
- [x] Hoan thien full CRUD user microservice.
- [x] Rename folder `src/main/java/com/example/microservice/Controller` thanh `controller`.
- [x] Them integration tests cho create/get/list/update/delete user.
- [x] Them Spring Boot Actuator health endpoint.
- [x] Tat `spring.jpa.open-in-view`.
- [x] Chot local database workflow bang XAMPP MySQL.
- [x] Bo Config/Eureka/Feign dependency chua dung de giu app nhe.
- [x] Tao smoke-test script cho first run.
- [x] Chuyen project sang monorepo layout nhe: root parent + `services/user-service`.
- [x] Tao gateway module nhe de route request den user-service.

## Tiep theo
- [ ] User tu chay thu bang XAMPP MySQL va `scripts/smoke-test.ps1`.
- [ ] Tao `shared` module that khi can dung chung response/error.
- [ ] Them service nghiep vu thu hai neu muon hoc inter-service communication.
- [ ] Chi them distributed-system tool khi microservice don dau tien da chay on.

## Phat hien, chua sua
- `application.yaml` runtime dang dung MySQL local voi password rong. Neu MySQL may khac config, app se fail khi start.
- `mvn test` co Mockito dynamic agent warning khi chay Java 23. Chua can sua trong phase nay.
