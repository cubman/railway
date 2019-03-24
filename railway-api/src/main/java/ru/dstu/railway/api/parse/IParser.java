package ru.dstu.railway.api.parse;

@FunctionalInterface
public interface IParser<T> {
    T parse();
}
