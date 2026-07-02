// src/main/java/com/ecommerce/ecommerce/repository/ProductoRepository.java
package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByNombreContaining(String nombre);

    List<Producto> findByCategoriaNombreContainingIgnoreCase(String nombre);
}
