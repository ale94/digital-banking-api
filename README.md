# 🏦 Digital Banking API

## 📚 Descripción

**Digital Banking API** es una aplicación RESTful desarrollada con **Spring Boot** que permite la gestión completa de un sistema bancario digital.  
Incluye operaciones para **usuarios**, **cuentas bancarias**, **depósitos**, **retiros**, **transferencias** y **consultas de movimientos**, garantizando seguridad y escalabilidad.

El proyecto está diseñado siguiendo buenas prácticas de **arquitectura en capas** (Controller - Service - Repository), con soporte para **Spring Security** y autenticación mediante **JWT**.

---

## 🚀 Funcionalidades

✅ Registro y autenticación de usuarios.  
✅ Creación y administración de cuentas bancarias.  
✅ Depósitos y retiros con validaciones.  
✅ Transferencias entre cuentas.  
✅ Consulta de balances y movimientos.  
✅ Manejo de errores y validaciones personalizadas.  
✅ Documentación de API con **Swagger/OpenAPI**.  

---

## 🛠️ Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA (Hibernate)**
- **Spring Security + JWT**
- **PostgreSQL**
- **Maven**
- **Swagger / OpenAPI**
- **Docker**

---

## 🔑 Endpoints Principales
- 👤 Autenticación

    - POST /api/auth/register → Registrar un nuevo usuario.

    - POST /api/auth/login → Autenticarse y obtener token JWT.

- 🏦 Cuentas

    - POST /api/accounts → Crear una cuenta para un usuario.

    - GET /api/accounts/{id} → Consultar detalles de una cuenta.

    - GET /api/accounts → Listar todas las cuentas.

- 💰 Operaciones

    - POST /api/accounts/{id}/deposit → Depositar dinero.

    - POST /api/accounts/{id}/withdraw → Retirar dinero.

    - POST /api/accounts/transfer → Transferir entre cuentas.

- 📊 Movimientos

    - GET /api/accounts/{id}/transactions → Consultar historial de transacciones.

