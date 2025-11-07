# 🚀 API Gateway Service

The **API Gateway Service** acts as the single entry point for all microservices in the ecosystem.  
It routes incoming requests to **Product Service**, **Order Service**, and **Inventory Service**.  
Built on top of **Spring Cloud Gateway** (reactive, WebFlux-based).

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


## 🧩 Features

- ⚡ Reactive, non-blocking routing (WebFlux)
- 🔐 OAuth2 / JWT authentication via **Keycloak**
- 🌐 Centralized API entry point for all microservices
- ⚙️ Dynamic routing through environment configuration
- 🐳 Easy containerization with Docker & Docker Compose
- 🔎 Service Discovery: integrated with Eureka for dynamic routing
- 🛡 Resilience: circuit breakers, retries, and timeouts via Resilience4J
- 🌐 Rate limiting using Redis
---
## 🚀 Run Locally
1.  Start docker-compose :
```bash
    docker-compose -f docker-compose.local.yml up --build -d
```
2. Start docker-compose
    ```bash
    docker-compose -f docker-compose.dockerfile.local.yml up --build -d
    ```
   or using Maven
```bash
mvn spring-boot:run
```

3. The Gateway will be available at:
👉 http://localhost:9000

## 🔐 Authentication (Keycloak Integration)
Every request to the Gateway must include a valid JWT token issued by Keycloak:
🔐 Keycloak / OAuth2 Integration

The API Gateway uses Keycloak as an OAuth2 Authorization Server to validate JWT tokens and secure all API endpoints.

✅ OpenID Discovery Endpoint

To fetch the authorization server configuration:

GET http://localhost:8181/realms/MicroServicesGrid-realm/.well-known/openid-configuration


Key OpenID Connect endpoints:

```
   {
   "issuer": "http://localhost:8181/realms/MicroServicesGrid-realm",
   "authorization_endpoint": "http://localhost:8181/realms/MicroServicesGrid-realm/protocol/openid-connect/auth",
   "token_endpoint": "http://localhost:8181/realms/MicroServicesGrid-realm/protocol/openid-connect/token",
   "userinfo_endpoint": "http://localhost:8181/realms/MicroServicesGrid-realm/protocol/openid-connect/userinfo",
   "end_session_endpoint": "http://localhost:8181/realms/MicroServicesGrid-realm/protocol/openid-connect/logout",
   "jwks_uri": "http://localhost:8181/realms/MicroServicesGrid-realm/protocol/openid-connect/certs"
   }
```
🎫 Obtaining an Access Token (curl)
````
   curl -X POST "http://localhost:8181/realms/MicroServicesGrid-realm/protocol/openid-connect/token" \
   -H "Content-Type: application/x-www-form-urlencoded" \
   -d "client_id=microservice-client" \
   -d "username=user" \
   -d "password=pass" \
   -d "grant_type=password"
````

📦 Example Request via Gateway with JWT Token
curl -X GET "http://localhost:9000/api/v1/products" \
-H "Authorization: Bearer <ACCESS_TOKEN>"

🧾 Keycloak Client Settings (Example)

|  Setting | Value  |
|---|---|
|  Client ID |   microservice-client|
| Client Type  |  Public |
|  Access Type |  public |
| Standard Flow  |✅   |
| Direct Access Grants  |  ✅ |


This shows how API Gateway integrates with Keycloak for OAuth2 authentication and token validation.
### 🔗 Available Routes ##

Service            | Route Prefix          | Example URL
------------------ | -------------------- | -----------------------------------------
🛍️ Product Service | /api/v1/products/**  | http://localhost:9000/api/v1/products
📦 Inventory Service | /api/v1/inventory/** | http://localhost:9000/api/v1/inventory
📑 Order Service    | /api/v1/orders/**    | http://localhost:9000/api/v1/orders

All endpoints require a valid Keycloak JWT token.

## 🧠 Project Context
Part of the MicroservicesGrid ecosystem — a modular, reactive microservices architecture built with Spring Boot 3.
Dynamic routing is supported via Eureka service discovery, and Resilience4J provides fault tolerance, including circuit breakers, retries, and timeouts. Rate limiting is enforced using Redis.

👨‍💻 **Author**: Andrij72 —  demo project using reactive stack, part of ***MicroservicesGrid*** ecosystem.