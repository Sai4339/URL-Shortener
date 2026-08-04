# 🔗 URL Shortener API

A RESTful URL Shortener application built using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL**. The application converts long URLs into short, unique links and redirects users to the original website while tracking click statistics.

---

## 🚀 Features

* Generate unique short URLs from long URLs
* Redirect users to the original URL using the short code
* Track the number of clicks for each shortened URL
* Store URL creation timestamp
* Prevent duplicate entries for the same original URL
* RESTful API design
* Layered Architecture (Controller → Service → Repository)
* MySQL database integration using Spring Data JPA
* Request validation using Spring Validation

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Lombok
* Postman (API Testing)

---

## 📂 Project Structure

```text
src
 └── main
      ├── java
      │     └── com.sai.urlshortener
      │            ├── controller
      │            ├── dto
      │            ├── entity
      │            ├── repository
      │            ├── service
      │            ├── exception
      │            └── UrlshortenerApplication
      │
      └── resources
            └── application.properties
```

---

## 🗄️ Database Schema

| Column       | Description            |
| ------------ | ---------------------- |
| id           | Primary Key            |
| original_url | Original long URL      |
| short_code   | Generated short code   |
| click_count  | Number of redirects    |
| created_at   | URL creation timestamp |

---

## 📡 API Endpoints

### 1. Shorten URL

**POST**

```http
POST /api/shorten
```

### Request Body

```json
{
    "originalUrl":"https://www.google.com"
}
```

### Response

```json
{
    "originalUrl":"https://www.google.com",
    "shortCode":"Ab12Cd"
}
```

---

### 2. Redirect URL

**GET**

```http
GET /{shortCode}
```

Example

```http
GET /Ab12Cd
```

This redirects the user to the original website and increments the click count.

---

### 3. URL Statistics

**GET**

```http
GET /api/stats/{shortCode}
```

### Sample Response

```json
{
    "originalUrl":"https://www.google.com",
    "shortCode":"Ab12Cd",
    "clickCount":15,
    "createdAt":"2026-08-04T18:10:21"
}
```

---

## ▶️ Running the Project Locally

### Clone the repository

```bash
git clone https://github.com/Sai4339/URL-Shortener
```

### Configure MySQL

Create a database:

```sql
CREATE DATABASE url_shortener;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Run the application

```bash
./mvnw spring-boot:run
```

---

## 🧪 Testing

Use Postman to test the REST APIs.

Example:

```
POST http://localhost:8080/api/shorten
```

```
GET http://localhost:8080/{shortCode}
```

```
GET http://localhost:8080/api/stats/{shortCode}
```

---

## 💡 Future Improvements

* User Authentication (JWT)
* Custom Short URLs
* QR Code Generation
* URL Expiration
* Analytics Dashboard
* Docker Support
* Cloud Deployment

---

## 📚 Key Concepts Demonstrated

* REST API Development
* Spring Boot
* Spring Data JPA
* Hibernate ORM
* MySQL Database Integration
* DTO Pattern
* Layered Architecture
* Exception Handling
* Input Validation
* HTTP Redirects

---
