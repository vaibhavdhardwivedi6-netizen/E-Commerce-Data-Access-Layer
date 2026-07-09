<img width="1145" height="822" alt="image" src="https://github.com/user-attachments/assets/236cb3c2-49b2-4f03-962a-d3645686e5aa" /># 🛒 Scalable Multi-Vendor E-Commerce Data Access Layer

A scalable backend application built using **Spring Boot**, **Hibernate (JPA)**, and **MySQL** that provides a complete data access layer for a multi-vendor e-commerce platform. The project demonstrates clean architecture, optimized database design, RESTful APIs, and efficient entity relationships.

---

## 🚀 Features

- 👤 User Management
- 🏪 Vendor Management
- 📦 Product Management
- 📂 Category Management
- 🛒 Shopping Cart Management
- 📋 Order & Order Item Management
- 👥 Customer Profile Management
- 📄 Pagination Support
- 🔍 Database Indexing for Faster Queries
- ♻️ Soft Delete Implementation (Hibernate)
- ⚡ Optimized Hibernate Entity Relationships
- ❌ Custom Exception Handling
- 🏗️ Layered Architecture
- 🌐 RESTful APIs

---

## 🛠️ Tech Stack

### Backend

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate ORM
- Maven

### Database

- MySQL

### API Testing

- Postman

### Build Tool

- Maven

---

## 📁 Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── exception
 ├── config
 └── resources
```

---

## 📊 Entity Relationship

- User ↔ CustomerProfile (One-to-One)
- CustomerProfile ↔ Cart (One-to-One)
- CustomerProfile ↔ Order (One-to-Many)
- Order ↔ OrderItem (One-to-Many)
- Product ↔ Category (Many-to-One)
- Product ↔ Vendor (Many-to-One)
- Cart ↔ Product (Many-to-Many)

---

## 📦 REST APIs

### User

```
POST   /users
GET    /users
GET    /users/{id}
PUT    /users/{id}
DELETE /users/{id}
```

### Customer Profile

```
POST   /profiles
GET    /profiles
GET    /profiles/{id}
PUT    /profiles/{id}
DELETE /profiles/{id}
```

### Vendor

```
POST   /vendors
GET    /vendors
GET    /vendors/{id}
PUT    /vendors/{id}
DELETE /vendors/{id}
```

### Category

```
POST   /categories
GET    /categories
GET    /categories/{id}
PUT    /categories/{id}
DELETE /categories/{id}
```

### Product

```
POST   /products
GET    /products
GET    /products/{id}
PUT    /products/{id}
DELETE /products/{id}
```

### Cart

```
POST   /cart/{customerId}/add/{productId}
DELETE /cart/{customerId}/remove/{productId}
GET    /cart/{customerId}
DELETE /cart/{customerId}/clear
```

### Order

```
POST   /orders
GET    /orders
GET    /orders/{id}
PUT    /orders/{id}
DELETE /orders/{id}
```

### Order Item

```
POST   /order-items
GET    /order-items
GET    /order-items/{id}
PUT    /order-items/{id}
DELETE /order-items/{id}
```

---

## ⚡ Hibernate Features Used

- One-to-One Mapping
- One-to-Many Mapping
- Many-to-One Mapping
- Many-to-Many Mapping
- Lazy Fetching
- Cascade Operations
- Orphan Removal
- @MapsId
- Soft Delete using @SQLDelete
- @SQLRestriction
- Database Indexing
- Pagination with Spring Data JPA

---

## 📈 Performance Optimizations

- Database Indexing
- Soft Delete
- Lazy Loading
- Pagination
- DTO Pattern
- ResponseEntity
- Layered Architecture

---

## 📸 API Testing

All APIs have been tested successfully using **Postman**.

---

## 🔮 Future Enhancements

- JWT Authentication
- Spring Security
- Role-Based Authorization
- Product Image Upload
- Payment Gateway Integration
- Order Tracking
- Email Notifications
- Dashboard Analytics
- Frontend Integration (HTML, CSS, Bootstrap, JavaScript)

---

## 👨‍💻 Author

**Vaibhav Dhar Dwivedi**

Java Full Stack Developer

---

## ⭐ If you found this project useful, consider giving it a Star.<img width="892" height="950" alt="Screenshot 2026-07-09 123804" src="https://github.com/user-attachments/assets/2b1163d0-2a35-4198-8489-4cd2e05c4d13" />

