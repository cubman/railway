package ru.dstu.railway.parse;

@FunctionalInterface
public interface IParser<T> {
    T parse();
}
