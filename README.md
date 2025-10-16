# 🚀 API Gateway Service

The **API Gateway Service** acts as the single entry point for all microservices in the ecosystem.  
It routes incoming requests to **Product Service**, **Order Service**, and **Inventory Service**.  
Built on top of **Spring Cloud Gateway** (reactive, WebFlux-based).

---

## 📌 Tech Stack
- Java 21 
- Spring Boot 3.x
- Spring Cloud Gateway (2023.x)
- Maven
- Docker / Docker Compose
---

## 🚀 Run Locally

1. Start the service:
```bash
mvn spring-boot:run
```
# 🚀 API Gateway Service

The **API Gateway Service** is the single **reactive entry point** for all microservices in the **MicroservicesGrid** ecosystem.  
It routes external requests to backend services and secures them with **JWT tokens** issued by **Keycloak**.

Built on **Spring Cloud Gateway** (WebFlux, Netty).

---

## 🧩 Features

- ⚡ Reactive, non-blocking routing (WebFlux)
- 🔐 OAuth2 / JWT authentication via **Keycloak**
- 🌐 Centralized API entry point for all microservices
- ⚙️ Dynamic routing through environment configuration
- 🐳 Easy containerization with Docker & Docker Compose

---

## ⚙️ Tech Stack

| Component                  | Version / Tool |
|----------------------------|----------------|
| ☕ Java                     | 21 |
|  Spring Boot               | 3.x (Reactive) |
| ☁️ Spring Cloud Gateway    | 2023.x |
| 🔐 Spring Security         | OAuth2 Resource Server |
| 🧩 Keycloak                | 24.x |
| 🐳 Docker / Docker Compose | Latest |
| Maven                      | 3.x |

---

## 🗺️ Routes Configuration

Defined in `application.properties`:

```properties
gateway.product-service.uri=http://localhost:8080
gateway.order-service.uri=http://localhost:8083
gateway.inventory-service.uri=http://localhost:8082
server.port=9000
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8181/realms/MicroServicesGrid-realm
```
## 🚀 Run Locally
▶️ Using Maven
```bash
Copy code
mvn spring-boot:run
```
🐳 Using Docker
```bash
docker-compose -f docker-compose.local.yml up --build -d
```
The Gateway will be available at:
👉 http://localhost:9000

## 🔐 Authentication (Keycloak Integration)
Every request to the Gateway must include a valid JWT token issued by Keycloak:


#### Authorization:  Bearer <access_token>
To obtain a token manually:
Authorization: Bearer <access_token>

``` bash
curl -X POST "http://localhost:8181/realms/MicroServicesGrid-realm/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=microservice-client" \
  -d "username=user" \
  -d "password=pass" \
  -d "grant_type=password"
  ```
### 🔗 Available Routes ##

S# 🔗 Available Routes

Service            | Route Prefix          | Example URL
------------------ | -------------------- | -----------------------------------------
🛍️ Product Service | /api/v1/products/**  | http://localhost:9000/api/v1/products
📦 Inventory Service | /api/v1/inventory/** | http://localhost:9000/api/v1/inventory
📑 Order Service    | /api/v1/orders/**    | http://localhost:9000/api/v1/orders

All endpoints require a valid Keycloak JWT token.


## 🧠 Project Context
Part of the MicroservicesGrid ecosystem —
a modular, reactive microservices architecture built with
Spring Boot 3.



👨‍💻 **Author**: Andrij72 —  demo project using reactive stack, part of ***MicroservicesGrid*** ecosystem.