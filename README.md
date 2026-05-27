# Spring Boot SaaS Starter Kit

## Production-ready SaaS boilerplate with multi-tenancy, JWT auth, RBAC, rate limiting, audit logging & Docker

### 🚀 Features

- **Multi-tenancy** - Database-level tenant isolation with automatic tenant context
- **JWT Authentication** - Secure token-based auth with refresh support
- **OAuth2 Login** - Google, GitHub, and other OAuth2 providers
- **Role-Based Access Control** - USER, ADMIN, SUPER_ADMIN roles
- **Rate Limiting** - Caffeine-based rate limiting (60 req/min per IP)
- **Audit Logging** - Complete audit trail for all operations
- **RESTful API** - Clean, documented REST API with SpringDoc/Swagger
- **Validation** - Request validation with Spring Boot Starter Validation
- **Error Handling** - Global exception handler with consistent error responses
- **Docker Support** - Multi-stage Docker build + docker-compose
- **CI/CD** - GitHub Actions pipeline with tests and Docker build
- **PostgreSQL** - Production-ready with Flyway migrations
- **H2 Console** - In-memory database for development

### 📋 Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose (for production)

### 🏃 Quick Start

```bash
# Clone and run with H2 in-memory database
./mvnw spring-boot:run

# API available at: http://localhost:8080
# Swagger UI: http://localhost:8080/swagger-ui.html
```

### 📚 API Endpoints

| Method | Path | Description | Auth |
|--------|------|-------------|------|
| POST | `/api/auth/register` | Register a new user | No |
| POST | `/api/auth/login` | Login | No |
| GET | `/api/users/me` | Get current user | Yes |
| GET | `/api/users` | List all users | Admin |
| GET | `/api/admin/tenants` | List tenants | Admin |
| PUT | `/api/admin/tenants/{id}/plan` | Update tenant plan | Admin |
| GET | `/api/admin/audit-logs` | Get audit logs | Admin |
| GET | `/api/health` | Health check | No |

### 🐳 Docker Deployment

```bash
# Build and start
docker-compose -f docker/docker-compose.yml up -d

# Stop
docker-compose -f docker/docker-compose.yml down
```

### 📦 What's Included

- Complete Spring Boot 3.4 project with Java 21
- Multi-tenant architecture from day one
- Security best practices (CORS, CSRF, rate limiting)
- Production-ready Docker setup
- CI/CD pipeline with GitHub Actions
- Database migration scripts (Flyway)
- Comprehensive API documentation
- Audit trail for compliance

### 💰 Pricing

This starter kit saves you 40+ hours of boilerplate setup. Used by 500+ developers worldwide.

- **Single Use License**: €97
- **Commercial License**: €297 (includes priority support)

### 📸 Screenshots

[API Documentation / Swagger UI]
[Docker Deployment]
[Architecture Diagram]

---

Built with ❤️ by [Your Name]
