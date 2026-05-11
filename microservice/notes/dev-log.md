# Task
Chuyen sang monorepo layout nhe va giu first service runnable.

# Toi hoc duoc gi?
- Controller chi nhan request, service giu nghiep vu.
- Validation va exception handler giup API tra loi sach.
- MockMvc test di qua HTTP layer ma khong can start server that.
- Test profile H2 giup test khong phu thuoc MySQL local.
- Actuator cho health endpoint phuc vu monitor.
- XAMPP MySQL hop hon Docker khi may thieu dung luong.
- Bo Config/Eureka/Feign giup app nhe hon va de chay hon.
- README va smoke test giup nguoi moi chay thu nhanh.
- Monorepo parent POM giup tach service ra `services/user-service` ma van build tu root.
- `gateway` va `shared` co the de scaffold, khong can full stack ngay.

# File da sua
- pom.xml
- services/user-service/pom.xml
- gateway/README.md
- shared/README.md
- README.md
- PROJECT.md
- TODO.md
- CHANGELOG.md
- DECISIONS.md
- notes/dev-log.md

# Vi sao sua vay?
De project co structure gan repo multi-service, nhung van nhe: chi `user-service` la app chay that, `gateway` va `shared` de phase sau.

# Lenh da chay
- mvn test -> fail lan dau do sandbox chan Maven download Spring Boot parent.
- mvn test -> BUILD SUCCESS, 9 tests pass.

# Loi gap phai
- Maven can network de tai Spring Boot parent POM lan dau.
- Mockito dynamic agent warning khi test chay bang Java 23; da ghi TODO, chua sua trong task nay.
