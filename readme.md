# 🛡️ Hermes — Minecraft Backend with Spring Boot

Hermes is a **RESTful backend** developed in **Java with Spring Boot** for managing Minecraft player data (users, economy, statistics) and authenticating multiple Minecraft servers using **JWT**. It includes **Redis caching**, persistence with **MySQL**, and is fully **dockerized** for development and production.

---

## 🧠 What is Hermes?

Hermes is a backend system that:

* Manages **Minecraft player data**.
* Authenticates **Minecraft servers** with credentials and JWT.
* Supports multiple server instances with synchronized data via Redis.
* Provides secure token handling (access and refresh tokens).
* Ready to run with Docker + Docker Compose.

This project is designed as a robust Minecraft backend foundation (economy, achievements, statistics, etc.).

---

## 🚀 Technologies

| Component      | Technology                   |
| -------------- | ---------------------------- |
| Backend        | Java 17 + Spring Boot        |
| Persistence    | MySQL                        |
| Cache          | Redis                        |
| Authentication | JWT                          |
| Build          | Gradle (with Gradle Wrapper) |
| Containers     | Docker + Docker Compose      |

---

## 🧱 Project Structure

```
hermes/
├─ src/
│  ├─ main/java/...       # Main code
│  ├─ main/resources      # Configs and startup SQL
│  └─ test/               # Tests (if any)
├─ gradlew
├─ build.gradle.kts       # Gradle build
├─ settings.gradle.kts
├─ Dockerfile
├─ docker-compose.yml
├─ .env-template          # Example environment variables
└─ README.md              # ← This file
```

---

## 🛠️ Requirements

* **Docker** & **Docker Compose** (V2 integrated)
* (Optional local) Java 17 + Gradle

---

## 🚀 How to run Hermes with Docker

1. Copy the environment template:

```bash
cp .env-template .env
```

2. Edit `.env` with your secrets (JWT, MySQL, Redis, etc.)

3. Start all services:

```bash
docker compose --env-file .env up --build
```

This will launch:

✔️ MySQL
✔️ Redis
✔️ Hermes backend

4. To run in detached mode:

```bash
docker compose --env-file .env up -d --build
```

5. Check running containers:

```bash
docker compose ps
```

6. View logs in real-time:

```bash
docker compose logs -f hermes
```

7. Stop all containers:

```bash
docker compose down
```

---

## ✨ Main Endpoints

### 🔐 Server Authentication

| Method | Endpoint               | Description                         |
| ------ | ---------------------- | ----------------------------------- |
| POST   | `/api/v1/auth/login`   | Server login with credentials       |
| POST   | `/api/v1/auth/refresh` | Refresh token to renew access token |

Returns **accessToken** and **refreshToken**.

---

### 👤 Users

| Method | Endpoint             | Description            |
| ------ | -------------------- | ---------------------- |
| GET    | `/api/v1/users`      | List users (paginated) |
| GET    | `/api/v1/users/{id}` | Get user by ID         |
| POST   | `/api/v1/users`      | Create a new user      |
| DELETE | `/api/v1/users/{id}` | Delete user            |

---

## 🔒 JWT and Tokens

* **Access Token**: expires in 1 day
* **Refresh Token**: expires in 7 days
* The client must handle automatic refresh if the access token expires.

This allows Minecraft servers to maintain sessions without resending sensitive credentials continuously.

---

## 📦 Main Entities

| Entity          | Key Fields                          |
| --------------- | ----------------------------------- |
| `Server`        | `id`, `password`                    |
| `User`          | `id`, `name`                        |
| `UserEconomy`   | `coins`, `gems`                     |
| `UserStatistic` | `kills`, `deaths`, `wins`, `losses` |

---

## 🧪 Data Initialization

Place a `data.sql` file in `src/main/resources` with initial inserts for:

* Initial servers
* Test users
* Economy and statistics

Spring Boot will execute it automatically on startup (configurable with `spring.jpa.hibernate.ddl-auto`).

---

## 📌 Environment Variables Example

```env
# MySQL
MYSQL_ROOT_PASSWORD=rootpassword
MYSQL_DATABASE=hermes
MYSQL_USER=hermesuser
MYSQL_PASSWORD=hermespass

# Redis
REDIS_PASSWORD=redispass

# Application
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/hermes
SPRING_DATASOURCE_USERNAME=hermesuser
SPRING_DATASOURCE_PASSWORD=hermespass

SPRING_REDIS_HOST=redis
SPRING_REDIS_PASSWORD=redispass

JWT_SECRET=yourSuperSecretJWT
```

---

## 🧭 Tips

* Use environment variables for **secrets**; do not hardcode sensitive values.
* Set `spring.jpa.hibernate.ddl-auto=update` to let Spring create tables automatically.
* Docker Compose volumes ensure MySQL data persists across container restarts.

---

## 📚 Documentation & References

For guidance on README structure and best practices: [GitHub Docs](https://docs.github.com/github/creating-cloning-and-archiving-repositories/creating-a-repository-on-github/about-readmes?utm_source=chatgpt.com)

---

## 📝 License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## 👏 Contributions

1. Fork the repo
2. Create a new feature branch
3. Submit a pull request with clear description
