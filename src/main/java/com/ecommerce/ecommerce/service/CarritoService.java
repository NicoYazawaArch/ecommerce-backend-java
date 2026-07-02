// src/main/java/com/ecommerce/ecommerce/service/CarritoService.java

package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.exception.CarritoNoEncontradoException;
import com.ecommerce.ecommerce.exception.StockInsuficienteException;
import com.ecommerce.ecommerce.model.Carrito;
import com.ecommerce.ecommerce.model.Producto;
import com.ecommerce.ecommerce.repository.CarritoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoService productoService;

    public CarritoService(CarritoRepository carritoRepository,
                          ProductoService productoService) {
        this.carritoRepository = carritoRepository;
        this.productoService = productoService;
    }

    public Carrito crear() {
        return carritoRepository.save(new Carrito());
    }

    public Carrito obtenerPorId(Integer id) {
        return carritoRepository.findById(id)
                .orElseThrow(() -> new CarritoNoEncontradoException(
                        "No se encontró un carrito con id " + id));
    }

    public List<Carrito> listarTodos() {
        return carritoRepository.findAll();
    }

    public Carrito agregarProducto(Integer carritoId, Integer productoId) {
        Carrito carrito = obtenerPorId(carritoId);
        Producto producto = productoService.obtenerPorId(productoId);

        if (producto.getStock() <= 0) {
            throw new StockInsuficienteException(
                    "El producto \"" + producto.getNombre() + "\" no tiene stock disponible.");
        }

        producto.setStock(producto.getStock() - 1);
        productoService.guardar(producto);

        carrito.getProductos().add(producto);
        return carritoRepository.save(carrito);
    }

    public Carrito vaciar(Integer id) {
        Carrito carrito = obtenerPorId(id);
        carrito.getProductos().clear();
        return carritoRepository.save(carrito);
    }

    public void eliminar(Integer id) {
        Carrito carrito = obtenerPorId(id);
        carritoRepository.delete(carrito);
    }
}
