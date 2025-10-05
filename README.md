# 🚀 API Gateway Service

The **API Gateway Service** acts as the single entry point for all microservices in the ecosystem.  
It routes incoming requests to **Product Service**, **Order Service**, and **Inventory Service**.  
Built on top of **Spring Cloud Gateway** (reactive, WebFlux-based).

---

## 📌 Tech Stack
- Java 17 / 21 (LTS)
- Spring Boot 3.x
- Spring Cloud Gateway (2023.x)
- Maven
- Docker / Docker Compose
- Azure App Service (for deployment)

---

## 🚀 Run Locally

1. Start the service:
```bash
mvn spring-boot:run
```
Тестові ендпоінти:

- http://localhost:8080/api/v1/products
  
- http://localhost:8080/api/v1/orders

- http://localhost:8080/api/v1/inventory
---

👨‍💻 **Author**: Andrij72 — — demo project using reactive stack, part of ***MicroservicesGrid*** ecosystem.