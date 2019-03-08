package ru.dstu.railway.exception;

/**
 * Ошибка - объект не уникален. Найден дубликат
 */
public class DuplicationException extends RuntimeException {
    public DuplicationException(String message) {
        super(message);
    }
}
