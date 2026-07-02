# Proyecto Final E-Commerce - Backend Java (Talento Tech)

API RESTful desarrollada en Java y Spring Boot para la gestión de un e-commerce. Incluye el catálogo de productos, gestión de categorías, lógica de carrito de compras y control dinámico de stock.

## 🚀 Tecnologías Utilizadas
* **Java 17+ / Spring Boot 3.2.5**
* **Spring Data JPA & Hibernate** para el mapeo objeto-relacional.
* **MySQL** como motor de base de datos.
* **Lombok** para reducción de código repetitivo (Getters, Setters, Constructores).
* **Frontend:** Interfaz interactiva en HTML, CSS (Bootstrap) y Vanilla JS.

## ⚙️ Arquitectura y Patrones
* **Arquitectura Multicapa:** Separación clara entre Controladores (`@RestController`), Servicios (`@Service`) y Repositorios (`@Repository`).
* **Manejo Centralizado de Excepciones:** Uso de `@ControllerAdvice` para capturar errores de validación (`@Valid`), entidades no encontradas y faltantes de stock, devolviendo respuestas HTTP semánticas (400, 404).
* **Relaciones JPA:** Implementación de `@ManyToOne` (Producto-Categoría) y `@ManyToMany` (Carrito-Producto).
* **CORS Global:** Configuración centralizada (`WebMvcConfigurer`) para permitir peticiones desde cualquier origen hacia el frontend.

## 🛠️ Instrucciones de Instalación y Ejecución

### 1. Configuración de la Base de Datos
El proyecto requiere una instancia de MySQL ejecutándose localmente en el puerto `3306`. 
* Las credenciales por defecto configuradas en el archivo `application.properties` son usuario `root` y contraseña `admin`. Modificar si es necesario para coincidir con el entorno local.
* Gracias a la propiedad `createDatabaseIfNotExist=true`, Hibernate se encargará de crear la estructura de tablas e inyectar el catálogo inicial automáticamente al arrancar.

### 2. Ejecutar el Servidor Java
Abrir una terminal en la raíz del proyecto y ejecutar el siguiente comando de Maven:
```bash
mvn clean spring-boot:run
