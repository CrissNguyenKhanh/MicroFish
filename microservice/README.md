# Microservice

Lightweight microservice monorepo dau tay.

## Can gi de chay
- Java 21+
- MySQL trong XAMPP

## Chay local
1. Start MySQL trong XAMPP.
2. Tao database `microservice_db` trong phpMyAdmin.
3. Chay service:

```powershell
mvn -pl services/user-service spring-boot:run
```

4. Check health:

```text
http://localhost:8080/actuator/health
```

## Test
Chay tu repo root:

```powershell
mvn test
```

## Smoke test
Chay app xong, mo PowerShell va go:

```powershell
.\scripts\smoke-test.ps1
```

## API chinh
- `POST /users`
- `GET /users`
- `GET /users/{id}`
- `PUT /users/{id}`
- `DELETE /users/{id}`
- `GET /actuator/health`

## Cau truc
- `services/user-service`: service dang chay
- `gateway`: scaffold api-gateway sau nay
- `shared`: scaffold code chung sau nay
