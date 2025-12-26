# QuickCommerce Backend

A Spring Boot backend service implementing **customer and order management** with **PostgreSQL**, following clean layered architecture and domain-driven design principles.

---

## 🧱 Architecture Overview

The application is organized into **domain-centric modules** with clear separation of concerns:


### Design Principles
- Layered architecture (Controller → Service → Entity → Repository)
- Domain logic encapsulated inside entities
- Transaction boundaries managed at service layer
- PostgreSQL-optimized ID generation using sequences
- Ready for future evolution into microservices or CQRS

---

## 🚀 Features

### Customer Management
- Create customers with configurable credit limits
- Credit reservation and release handled at domain level
- Persistent customer storage using PostgreSQL

### Order Management
- Create orders linked to customers
- Enforce customer credit limits during order creation
- Cancel orders with automatic credit rollback
- Order lifecycle managed via explicit status enum

### Technical
- Spring Boot + Spring Data JPA
- PostgreSQL with sequence-based ID generation
- JDBC batching enabled for high-performance inserts
- RESTful API design
- Maven Wrapper for consistent builds
- Clean Git configuration with `.gitignore` and `.gitattributes`

---

## 🗄️ Database Configuration

The application uses **PostgreSQL** with Hibernate sequence optimization:

```properties
spring.jpa.properties.hibernate.id.new_generator_mappings=true
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
spring.jpa.properties.hibernate.generate_statistics=false
