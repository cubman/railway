package ru.dstu.railway.parse.exception;

/**
 * Ошибка парсинга файла
 */
public class ParseException extends RuntimeException {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
