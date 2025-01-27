# Proyecto de Microservicios: Gestión de Usuarios y Roles

## **Descripción General**
Este proyecto está compuesto por dos microservicios desarrollados con Spring Boot:
1. **Microservicio de Usuarios (`micro-usuarios`)**: Gestiona las operaciones relacionadas con los usuarios.
2. **Microservicio de Roles (`micro-roles`)**: Gestiona las operaciones relacionadas con los roles.

Los microservicios interactúan entre sí para gestionar una relación **muchos a muchos** entre usuarios y roles, utilizando una tabla intermedia (`users_roles`) en la base de datos.

---

## **Microservicio de Usuarios**

### **Endpoints Principales**
1. **Crear un usuario**:
   - **Método**: `POST`
   - **URL**: `/api/users`
   - **Cuerpo**:
     ```json
     {
       "nombre": "John",
       "apellido": "Doe",
       "email": "john.doe@example.com",
       "fechaNacimiento": "1990-01-01T00:00:00",
       "telefono": "123456789"
     }
     ```

2. **Listar todos los usuarios**:
   - **Método**: `GET`
   - **URL**: `/api/users`

3. **Buscar un usuario por ID**:
   - **Método**: `GET`
   - **URL**: `/api/users/{id}`

4. **Eliminar un usuario**:
   - **Método**: `DELETE`
   - **URL**: `/api/users/{id}`

5. **Asignar un rol a un usuario**:
   - **Método**: `POST`
   - **URL**: `/api/users/{userId}/roles/{roleId}`

6. **Listar los roles asignados a un usuario**:
   - **Método**: `GET`
   - **URL**: `/api/users/{userId}/roles`

7. **Listar los usuarios con un rol específico**:
   - **Método**: `GET`
   - **URL**: `/api/users/roles/{roleId}`

8. **Revocar un rol de un usuario**:
   - **Método**: `DELETE`
   - **URL**: `/api/users/{userId}/roles/{roleId}`

---

## **Microservicio de Roles**

### **Endpoints Principales**
1. **Crear un rol**:
   - **Método**: `POST`
   - **URL**: `/api/roles`
   - **Cuerpo**:
     ```json
     {
       "nombre": "Admin",
       "descripcion": "Rol de administrador con acceso completo"
     }
     ```

2. **Listar todos los roles**:
   - **Método**: `GET`
   - **URL**: `/api/roles`

3. **Buscar un rol por ID**:
   - **Método**: `GET`
   - **URL**: `/api/roles/{id}`

4. **Eliminar un rol**:
   - **Método**: `DELETE`
   - **URL**: `/api/roles/{id}`

---

## **Relación Muchos a Muchos: Tabla `users_roles`**
La tabla intermedia `users_roles` permite gestionar la asignación de roles a usuarios. Cada entrada en esta tabla representa una relación entre un usuario y un rol.

### **Estructura de la Tabla `users_roles`**
```sql
CREATE TABLE users_roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    UNIQUE (user_id, role_id)
);
