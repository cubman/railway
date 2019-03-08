package ru.dstu.station.exception;

/**
 * Неподдерживаемая операция
 */
public class UnsupportedOperationException extends RuntimeException {
    public UnsupportedOperationException(String message) {
        super(message);
    }
}
