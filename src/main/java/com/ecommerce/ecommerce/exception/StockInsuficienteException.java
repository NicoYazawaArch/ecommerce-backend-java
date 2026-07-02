// src/main/java/com/ecommerce/ecommerce/exception/StockInsuficienteException.java

package com.ecommerce.ecommerce.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
