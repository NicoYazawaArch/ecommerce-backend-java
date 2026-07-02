// src/main/java/com/ecommerce/ecommerce/repository/CarritoRepository.java
package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.ecommerce.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}
