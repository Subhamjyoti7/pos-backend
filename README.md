Project Title

POS System – Backend Application

Description

The POS System Backend is a RESTful backend application developed using Java and Spring Boot to manage retail operations such as products, orders, inventory, customers, and employees. The system provides APIs that allow businesses to efficiently manage sales transactions and inventory data.

This project demonstrates backend development using Spring Boot, REST APIs, and relational databases.

Features

Product management (add, update, delete, view products)

Customer management

Employee management

Order processing

Inventory tracking

Refund management

REST API based architecture

Layered architecture (Controller, Service, Repository)

Technologies Used

Backend

Java

Spring Boot

Spring Data JPA

Hibernate

Database

MySQL

Tools

Maven

Docker

Postman

Git & GitHub

Architecture

The project follows a layered architecture:

Controller Layer → Handles API requests

Service Layer → Business logic

Repository Layer → Database interaction

API Endpoints (Example)
Product APIs

GET /products → Get all products
GET /products/{id} → Get product by ID
POST /products → Add new product
PUT /products/{id} → Update product
DELETE /products/{id} → Delete product

Order APIs

POST /orders → Create order
GET /orders → Get all orders

Installation & Setup
1. Clone the Repository
git clone https://github.com/Subhamjyoti7/pos-backend.git
2. Navigate to Project
cd pos-backend
3. Configure Database

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/pos_db
spring.datasource.username=root
spring.datasource.password=yourpassword
4. Run Application

Using Maven:

mvn spring-boot:run
Testing APIs

You can test APIs using:

Postman

Thunder Client

Curl

Deployment

The application is deployed on Render.

Live URL:
https://pos-backend-b0cd.onrender.com/

GitHub Repository

https://github.com/Subhamjyoti7/pos-backend

Author

Subhamjyoti Sahoo
Java Backend Developer | Spring Boot Developer

LinkedIn:
https://linkedin.com/in/subhamjyoti-sahoo

GitHub:
https://github.com/Subhamjyoti7
