package ru.dstu.railway.model.exception;

/**
 * Неизвестное состояние объекта
 */
public class UnknownStateCodeException extends RuntimeException {
    public UnknownStateCodeException(String message) {
        super(message);
    }
}
