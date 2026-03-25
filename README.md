# 📚 Spring Boot Books App

Ett Spring Boot-projekt för att hantera böcker (CRUD, validering, filtrering och paginering).

---

## 🚀 Starta projektet

### 1. Starta databasen (PostgreSQL via Docker)

```bash
docker compose up -d
```

### 2. Starta applikationen

```bash
./mvnw spring-boot:run
```

---

## 🗄️ Databas

| Inställning | Värde      |
| ----------- | ---------- |
| Databas     | mydatabase |
| Användare   | myuser     |
| Lösenord    | secret     |

---

## 🌐 URL

Öppna i webbläsaren:

http://localhost:8080

---

## ⚙️ Tekniker

* Spring Boot
* Spring MVC
* Thymeleaf
* Spring Data JPA
* PostgreSQL (Docker)
* Bean Validation

---

## 💡 Notering

Projektet använder PostgreSQL via Docker.
Se till att Docker är installerat och att databasen startas innan applikationen körs.