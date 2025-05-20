# 🛒 E-Commerce System - Spring Boot REST API

This is a full-featured **E-Commerce REST API** developed using **Spring Boot**. It supports user registration, product listing, cart management, order placement, and more. The system is designed to serve both customers and admins (sellers), with role-based access control and a well-structured database schema.

---

## 🚀 Features

- 👤 **User Registration & Authentication**
- 📦 **Product Management** (CRUD)
- 🛍 **Cart Operations**
- 📬 **Order Placement & Tracking**
- 🧾 **Address Management**
- 🛡️ **Role-Based Authorization (USER, ADMIN)**

---

## 📚 Tech Stack

- **Backend:** Spring Boot, Spring Data JPA, Spring Security
- **Database:** MySQL / PostgreSQL (configurable)
- **Build Tool:** Maven
- **API Tool:** Postman / Swagger (if available)

---


---

## 🔐 Authentication / User Endpoints

| Method | Endpoint              | Description                           |
|--------|-----------------------|---------------------------------------|
| POST   | `/api/user/register`  | Register a new user (buyer/seller)    |
| PUT    | `/api/user/update`    | Update profile details                |

---

## 🏠 Address Management

| Method | Endpoint                  | Description                |
|--------|---------------------------|----------------------------|
| GET    | `/api/address`            | Get user's saved addresses |
| PUT    | `/api/address/{id}`       | Update address             |

---

## 🛍 Product Management (Admin Only)

| Method | Endpoint              | Description       |
|--------|-----------------------|-------------------|
| POST   | `/api/products`       | Add new product   |
| GET    | `/api/products`       | List all products |
| GET    | `/api/products/{id}`  | Get product by ID |
| PUT    | `/api/products/{id}`  | Update product    |
| DELETE | `/api/products/{id}`  | Delete product    |

---

## 🛒 Cart Operations

| Method | Endpoint               | Description                   |
|--------|------------------------|-------------------------------|
| POST   | `/api/cart/add`        | Add product to cart           |
| GET    | `/api/cart`            | Get current user's cart items |
| PUT    | `/api/cart/{itemId}`   | Update quantity of cart item  |
| DELETE | `/api/cart/{itemId}`   | Remove item from cart         |

---

## 📦 Order Management

| Method | Endpoint                | Description                      |
|--------|-------------------------|----------------------------------|
| POST   | `/api/orders`           | Place a new order                |
| GET    | `/api/orders`           | List current user's orders       |
| GET    | `/api/orders/{id}`      | Get order details                |
| PUT    | `/api/orders/{id}`      | Update order status (Admin only)|

---

## 🛠 How to Run Locally

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/ecommerce-system.git
   cd ecommerce-system
## 📚 API Documentation
Access Swagger UI at: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) after starting the application

---

---

---

## ✨ Author

Crafted with ❤️ by  
**<span style="font-size:1.3em; font-weight:bold;">🌟 Sandesh Adhikari</span>**

---

