// src/main/java/com/ecommerce/ecommerce/exception/ProductoNoEncontradoException.java

package com.ecommerce.ecommerce.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
