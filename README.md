# AgroLink

A secure Contract Farming Platform built using **Java, Spring Boot, Spring Security, JWT, JPA, Hibernate and MySQL**. AgroLink connects farmers and buyers by enabling digital contract creation, negotiation, secure authentication and payment tracking through RESTful APIs.

---

## 1. Features

### Authentication & Authorization
- User Registration and Login
- JWT-based Authentication
- BCrypt Password Encryption
- Role-Based Access Control
- Roles:
    - Admin
    - Farmer
    - Buyer

---

### Contract Management
- Create Farming Contracts
- View All Contracts
- View Contract by ID
- View Farmer Contracts
- View Buyer Contracts
- Accept Contract
- Reject Contract
- Soft Delete Contracts
- Contract Status Tracking

---

### Payment Workflow
- Payment Status Tracking
    - Pending
    - Processing
    - Paid
- Automatic Contract Completion after successful payment

---

### Security
- Spring Security
- JWT Authentication Filter
- Stateless Session Management
- Protected REST APIs
- Ownership Validation

---

### Database
- MySQL
- Spring Data JPA
- Hibernate ORM
- UUID Primary Keys
- Entity Relationships

---

## 2. Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Secure Authentication |
| Spring Data JPA | ORM Layer |
| Hibernate | Database Mapping |
| MySQL | Database |
| Lombok | Boilerplate Reduction |
| Maven | Dependency Management |

---

## 3. Architecture

```
                Client (Postman)

                        │

                        ▼

               Spring Security Filter

                        │

                        ▼

            JWT Authentication Filter

                        │

                        ▼

                  Controller Layer

                        │

                        ▼

                   Service Layer

                        │

                        ▼

                 Repository Layer

                        │

                        ▼

                  MySQL Database
```

---

## 4. User Roles

### Farmer

- Register/Login
- Create Contracts
- View Own Contracts

---

### Buyer

- Register/Login
- View Assigned Contracts
- Accept Contracts
- Reject Contracts
- Update Payment Status

---

### Admin

- View All Contracts
- Delete Contracts
- Manage Users

---

##  5. Contract Lifecycle

```
Farmer Creates Contract
          │
          ▼
       PENDING
          │
Buyer Accepts Contract
          │
          ▼
      ACCEPTED
          │
Payment Started
          │
          ▼
PROCESSING
          │
Payment Successful
          │
          ▼
        PAID
          │
          ▼
     COMPLETED
```

---

##  6. API Endpoints

### Authentication

| Method | Endpoint |
|---------|----------|
| POST | `/api/auth/register` |
| POST | `/api/auth/login` |

---

### Contracts

| Method | Endpoint |
|---------|----------|
| POST | `/api/contracts` |
| GET | `/api/contracts` |
| GET | `/api/contracts/{id}` |
| GET | `/api/contracts/farmer/{id}` |
| GET | `/api/contracts/buyer/{id}` |
| PUT | `/api/contracts/{id}/accept` |
| PUT | `/api/contracts/{id}/reject` |
| PUT | `/api/contracts/{id}/complete` |
| PUT | `/api/contracts/{id}/payment?status=PAID` |
| DELETE | `/api/contracts/{id}` |

---

## 7. Run The Project

### Clone Repository

```bash
git clone https://github.com/Soumya-c0/AgroLink.git
```

### Run

```bash
mvn spring-boot:run
```
---