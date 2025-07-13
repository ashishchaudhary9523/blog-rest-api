# 📝 Spring Boot Blog REST API

A comprehensive Blog REST API built using Spring Boot, designed to handle user authentication, blog post management, comments, roles, and JWT-based security. This API provides the backend infrastructure for a blogging platform and follows RESTful principles.

---

## 📌 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Environment Variables](#environment-variables)
- [API Endpoints](#api-endpoints)
- [Authentication & Authorization](#authentication--authorization)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

---

## 🚀 Overview

This project is a RESTful web service that enables:

- User registration and login with JWT-based authentication.
- CRUD operations for blog posts and comments.
- Role-based access control.
- Pagination, filtering, and sorting of posts.
- Secure endpoints accessible only with proper authorization.

---

## ✨ Features

- ✅ RESTful architecture
- 🔐 JWT-based Authentication & Authorization
- 👥 Role-based access control (Admin & User)
- 📝 CRUD operations for Posts and Comments
- 📄 DTO and Entity mapping
- 💾 Spring Data JPA for database interactions
- ⚙️ Global Exception Handling
- 🧪 Unit and Integration Testing
- 🧹 Clean and layered architecture

---

## 🛠️ Tech Stack

- **Language:** Java 17+
- **Framework:** Spring Boot
- **Database:** H2 / MySQL / PostgreSQL
- **ORM:** Spring Data JPA, Hibernate
- **Security:** Spring Security, JWT
- **Build Tool:** Maven
- **Testing:** JUnit, Mockito
- **IDE:** IntelliJ IDEA / Eclipse

---

## 📁 Project Structure

```bash
springboot-blog-rest-api/
│
├── src/
│   ├── main/
│   │   ├── java/com/springboot/blog/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   └── SpringbootBlogRestApiApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
│       └── java/com/springboot/blog/
│           └── SpringbootBlogRestApiApplicationTests.java
│
├── pom.xml
├── .gitignore
└── README.md
```

---

## 🧰 Getting Started

### Prerequisites

- Java 17+
- Maven
- Git

### Steps to Run

```bash
# Clone the repository
git clone https://github.com/your-username/springboot-blog-rest-api.git

# Navigate into the project
cd springboot-blog-rest-api

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

Application will start at: `http://localhost:8080`

---

## ⚙️ Environment Variables

Set the following properties in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

app.jwt.secret=your_jwt_secret
app.jwt.expiration-milliseconds=604800000
```

---

## 🔌 API Endpoints

### Auth

| Method | Endpoint             | Description         |
|--------|----------------------|---------------------|
| POST   | `/api/auth/register` | Register new user   |
| POST   | `/api/auth/login`    | Login and get token |

### Posts

| Method | Endpoint             | Description                |
|--------|----------------------|----------------------------|
| GET    | `/api/posts`         | Get all posts              |
| GET    | `/api/posts/{id}`    | Get post by ID             |
| POST   | `/api/posts`         | Create new post (Admin)    |
| PUT    | `/api/posts/{id}`    | Update post (Admin)        |
| DELETE | `/api/posts/{id}`    | Delete post (Admin)        |

### Comments

| Method | Endpoint                              | Description              |
|--------|---------------------------------------|--------------------------|
| GET    | `/api/posts/{postId}/comments`        | Get comments for post    |
| POST   | `/api/posts/{postId}/comments`        | Add comment to a post    |
| PUT    | `/api/comments/{id}`                  | Update a comment         |
| DELETE | `/api/comments/{id}`                  | Delete a comment         |

---

## 🔐 Authentication & Authorization

- Users receive a **JWT token** after logging in.
- Include the token in the `Authorization` header as:

```
Authorization: Bearer <your_token_here>
```

- Role-based access is implemented:
  - `ROLE_ADMIN`: Can manage posts
  - `ROLE_USER`: Can comment

---

## ✅ Testing

To run tests:

```bash
mvn test
```

Includes unit and integration tests using:

- **JUnit 5**
- **Mockito**
- **Spring Boot Test**

---

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -am 'Add new feature'`
4. Push to the branch: `git push origin feature-name`
5. Create a pull request

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 🧑‍💻 Author

Developed by [Your Name](https://github.com/your-username)

Feel free to reach out for suggestions or improvements!
