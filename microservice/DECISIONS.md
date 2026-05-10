# Decisions

## 2026-05-10 - Them smoke test local
- Tai lieu run va script smoke test giup nguoi moi chay app nhanh hon.
- Smoke test di qua health, create, read, list, update, delete.
- Ly do: first microservice can co duong chay thu ro rang truoc khi hoc phan tan.

## 2026-05-10 - Uu tien first runnable microservice
- Giua stack nhe va co the chay duoc tren may hien tai.
- Tam thoi khong dua Config Server, Eureka, Feign vao duong chay chinh.
- Ly do: can mot microservice dau tien chay on, ro luong, de hoc va demo truoc.

## 2026-05-10 - Chon XAMPP MySQL thay Docker Compose
- May nguon han che dung luong nen khong dua Docker Compose vao phase nay.
- Dung MySQL cua XAMPP/local host de nhe hon va de chay hon tren may hien tai.
- Health endpoint van giu qua Actuator de monitor service.

## 2026-05-09 - Lam full CRUD truoc khi them ha tang microservice
- Hoan thien CRUD user trong mot service doc lap truoc.
- Docker Compose, Actuator, Eureka, Config Server de phase sau de tranh lam qua nhieu thu cung luc.
- Ly do: CRUD + tests la nen tang can chay xanh truoc khi them distributed-system tooling.

## 2026-05-09 - Tach runtime DB va test DB
- Runtime tiep tuc dung MySQL local de hoc luong microservice that voi database.
- Test dung H2 in-memory de `mvn test` chay doc lap, khong can MySQL.

## 2026-05-09 - Business logic nam o service
- Controller chi nhan request va tra response.
- Service xu ly nghiep vu nhu check email trung, save user, get user.
- Repository chi phu trach truy cap database.

## 2026-05-09 - Loi API tra bang global handler
- Dung `GlobalExceptionHandler` de response loi co format on dinh.
- Validation request dung Jakarta Validation tren DTO.
