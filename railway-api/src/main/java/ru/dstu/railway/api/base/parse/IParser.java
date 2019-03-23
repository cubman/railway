package ru.dstu.railway.api.base.parse;

@FunctionalInterface
public interface IParser<T> {
    T parse();
}
