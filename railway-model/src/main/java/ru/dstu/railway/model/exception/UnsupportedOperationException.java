package ru.dstu.railway.model.exception;

/**
 * Неподдерживаемая операция
 */
public class UnsupportedOperationException extends RuntimeException {
    public UnsupportedOperationException(String message) {
        super(message);
    }
}
