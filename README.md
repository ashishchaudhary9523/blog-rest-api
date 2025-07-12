# 📝 Spring Boot Blog REST API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.5.3-brightgreen)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

A robust and scalable RESTful Blog API built with **Spring Boot**, designed to handle user authentication, post management, comments, and more.

---

## ✨ Features

- 🔐 JWT-based Authentication & Authorization
- 📝 CRUD for Posts and Comments
- 👥 User Registration & Login
- 🧾 Validation and Exception Handling
- 🧪 Unit and Integration Testing
- 📦 Maven for Dependency Management
- 🐘 PostgreSQL Database Integration

---

## 🔧 Technologies Used

- Java 21
- Spring Boot 3.5.3
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (io.jsonwebtoken)
- Lombok

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL

### Setup

```bash
# Clone the repository
git clone https://github.com/your-username/springboot-blog-rest-api.git
cd springboot-blog-rest-api

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

---

## 📁 Project Structure

```
src
├── main
│   ├── java/com/springboot/blog
│   │   ├── controller
│   │   ├── dto
│   │   ├── entity
│   │   ├── exception
│   │   ├── repository
│   │   ├── security
│   │   └── service
│   └── resources
│       └── application.properties
└── test
```

---

## 📬 API Endpoints

| Method |   | Endpoint                       | Description         |
|--------|:--|--------------------------------|---------------------|
| POST   |   | `/api/auth/sign-up`            | Register a new user |
| POST   |   | `/api/auth/sign-in`            | Authenticate user   |
| GET    |   | `/api/posts/all-posts`         | Get all posts       |
| POST   |   | `/api/posts/create`            | Create a new post   |
| PUT    |   | `/api/posts/update-post/{id}`  | Update a post       |
| PUT    |   | `/api/posts/update-image/{id}` | Update a post       |
| DELETE |   | `/api/posts/delete-post/{id}`  | Delete a post       |
| POST   |   | `/api/posts/{id}/comments`     | Comment on a post   |

- and much more to explore...

---

## 🛡️ License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙌 Contributing

Contributions are welcome! Feel free to fork the repo and submit a PR.

---

## ✨ Author

- **Ashish** — [GitHub Profile](https://github.com/the-hunter-web)
