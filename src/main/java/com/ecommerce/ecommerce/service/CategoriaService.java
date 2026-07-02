// src/main/java/com/ecommerce/ecommerce/service/CategoriaService.java

package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.exception.CategoriaNoEncontradaException;
import com.ecommerce.ecommerce.model.Categoria;
import com.ecommerce.ecommerce.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listarTodas() {
        return repository.findAll();
    }

    public Categoria obtenerPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoriaNoEncontradaException(
                        "No se encontró una categoría con id " + id));
    }

    public Categoria guardar(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria actualizar(Integer id, Categoria datos) {
        Categoria c = obtenerPorId(id);
        c.setNombre(datos.getNombre());
        c.setDescripcion(datos.getDescripcion());
        return repository.save(c);
    }

    public void eliminar(Integer id) {
        Categoria c = obtenerPorId(id);
        repository.delete(c);
    }
}
