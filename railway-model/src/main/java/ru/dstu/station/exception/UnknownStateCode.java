package ru.dstu.station.exception;

public class UnknownStateCode extends RuntimeException {
    public UnknownStateCode(String message) {
        super(message);
    }
}
