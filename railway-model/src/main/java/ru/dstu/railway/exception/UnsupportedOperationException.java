package ru.dstu.railway.exception;

/**
 * Неподдерживаемая операция
 */
public class UnsupportedOperationException extends RuntimeException {
    public UnsupportedOperationException(String message) {
        super(message);
    }
}
