# Task
Them gateway nhe de project co flow gan Bookteria hon: client -> gateway -> user-service.

# Toi hoc duoc gi?
- Gateway la entrypoint cho client, service ben trong co the nam port rieng.
- Port 8080 danh cho gateway, port 8081 danh cho user-service.
- Gateway co the route request ma chua can Eureka/Config Server.
- Spring MVC `RestClient` co the lam proxy nhe cho phase dau.
- Neu Spring Boot khong tu tao `RestClient.Builder`, minh tao bean `RestClient.builder()`.
- Actuator health nen co tren ca gateway va service.

# File da sua
- pom.xml
- gateway/pom.xml
- gateway/src/main/java/com/example/gateway/GatewayApplication.java
- gateway/src/main/java/com/example/gateway/controller/ProxyController.java
- gateway/src/main/resources/application.yaml
- gateway/src/test/java/com/example/gateway/GatewayApplicationTests.java
- services/user-service/src/main/resources/application.yaml
- PROJECT.md
- TODO.md
- CHANGELOG.md
- DECISIONS.md
- notes/dev-log.md

# Vi sao sua vay?
De co duong chay microservice ro hon: client goi gateway `:8080`, gateway day request sang user-service `:8081`. Cach nay gan repo multi-service nhung van nhe, khong can Docker/Eureka/Config Server.

# Lenh da chay
- mvn test -> fail do thieu bean `RestClient.Builder` trong gateway.
- mvn test -> BUILD SUCCESS, 10 tests pass.

# Loi gap phai
- Gateway test fail vi `ProxyController` can `RestClient.Builder` nhung context khong co bean nay. Da them bean trong `GatewayApplication`.
- Mockito dynamic agent warning khi test chay bang Java 23; da ghi TODO, chua sua trong task nay.
