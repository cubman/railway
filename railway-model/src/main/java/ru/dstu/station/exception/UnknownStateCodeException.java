package ru.dstu.station.exception;

/**
 * Неизвестное состояние объекта
 */
public class UnknownStateCodeException extends RuntimeException {
    public UnknownStateCodeException(String message) {
        super(message);
    }
}
