# 🏦 Digital Banking API

## 📚 Descripción

Digital Banking API es una aplicación RESTful desarrollada con Spring Boot que proporciona funcionalidades basicas de un sistema bancario digital.

La API permite gestionar operaciones esenciales como el manejo de usuarios, cuentas bancarias, y la realización de depósitos, retiros y transferencias. Además, ofrece consultas del historial de movimientos, garantizando en todo momento la seguridad y escalabilidad de las transacciones.

El proyecto sigue una arquitectura en capas (Controlador - Servicio - Repositorio) y cuenta con Spring Security para la protección de los endpoints, utilizando JWT (JSON Web Tokens) para la autenticación de usuarios.

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
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security + JWT**
- **PostgreSQL**
- **Maven**
- **Swagger / OpenAPI**
- **Docker**

---

## 🔑 Endpoints Principales
### 👤 Autenticación
- `POST /digital_banking/login` - Autenticarse y obtener token JWT.

### 🏦 Usuarios
- `POST /digital_banking/api/users` - Crear un usuario.
- `GET /digital_banking/api/users` - Obtener todos los usuarios.
- `GET /digital_banking/api/users/{id}` - Obtener un usuario.
- `PUT /digital_banking/api/users/{id}` - Actualizar un usuario.
- `DELETE /digital_banking/api/users/{id}` - Borrar un usuario.
- `PATCH /digital_banking/api/users/{id}/change-password?password=modificada` - Cambiar contraseña.
- `PATCH /digital_banking/api/users/{id}/lock` - Bloquear un usuario.
- `PATCH /digital_banking/api/users/{id}/unlock` - Desbloquear un usuario.

### 💰 Cuentas
- `GET /digital_banking/api/accounts/balance/{id}` - Obtener el balance de una cuenta.
- `PATCH /digital_banking/api/accounts/transfer` - Realizar transferencia a otra cuenta.
- `PATCH /digital_banking/api/accounts/deposit/{id}` - Depositar dinero en cuenta.
- `PATCH /digital_banking/api/accounts/withdraw/{id}?amount=5000` - Retirar dinero de cuenta.

### 📊 Transacciones
- `GET /digital_banking/api/transactions/{id}` - Obtener una transaccion.
- `GET /digital_banking/api/download-pdf/{id}` - Obtener el comprobante de una transaccion.

---
