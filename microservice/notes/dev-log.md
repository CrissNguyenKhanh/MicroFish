# Task
Them duong chay thu dau tien: README + smoke test script, van giu stack nhe.

# Toi hoc duoc gi?
- Controller chi nhan request, service giu nghiep vu.
- Validation va exception handler giup API tra loi sach.
- MockMvc test di qua HTTP layer ma khong can start server that.
- Test profile H2 giup test khong phu thuoc MySQL local.
- Actuator cho health endpoint phuc vu monitor.
- XAMPP MySQL hop hon Docker khi may thieu dung luong.
- Bo Config/Eureka/Feign giup app nhe hon va de chay hon.
- README va smoke test giup nguoi moi chay thu nhanh.

# File da sua
- src/main/java/com/example/microservice/controller/HelloController.java
- src/main/java/com/example/microservice/controller/UserController.java
- src/main/java/com/example/microservice/dto/CreateUserRequest.java
- src/main/java/com/example/microservice/dto/UpdateUserRequest.java
- src/main/java/com/example/microservice/entity/User.java
- src/main/java/com/example/microservice/service/UserService.java
- src/main/java/com/example/microservice/exception/ApiErrorResponse.java
- src/main/java/com/example/microservice/exception/GlobalExceptionHandler.java
- src/main/resources/application.yaml
- src/test/java/com/example/microservice/MicroserviceApplicationTests.java
- src/test/java/com/example/microservice/UserControllerIntegrationTests.java
- src/test/java/com/example/microservice/HealthEndpointIntegrationTests.java
- src/test/resources/application-test.yaml
- pom.xml
- README.md
- scripts/smoke-test.ps1
- PROJECT.md
- TODO.md
- CHANGELOG.md
- DECISIONS.md

# Vi sao sua vay?
De user service co du CRUD co ban, co health endpoint, va co duong chay thu ro rang cho first microservice dau tien.

# Lenh da chay
- mvn clean test -> BUILD SUCCESS, 1 test pass
- mvn test -> lan dau fail do controller source bi mat khi doi case folder tren Windows
- mvn test -> BUILD SUCCESS, 8 tests pass
- mvn test -> BUILD SUCCESS, 9 tests pass
- mvn test -> BUILD SUCCESS, 9 tests pass, app khong con nhat cloud deps
- README + smoke test script them vao de demo nhanh hon

# Loi gap phai
- Spring Cloud Config import YAML sai format
- Maven can network de tai dependency lan dau
- Windows case-insensitive: add `controller` va delete `Controller` trong cung patch lam mat controller source. Da rename qua folder tam roi chay lai test pass.
- Docker khong duoc chon do may nguon han che dung luong; chuyen sang XAMPP MySQL local.
- Config/Eureka/Feign bo di de giu app nhe va ro muc tieu ban dau.
