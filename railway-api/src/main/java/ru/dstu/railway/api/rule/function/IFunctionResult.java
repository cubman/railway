package ru.dstu.railway.api.rule.function;

import java.util.List;

/**
 * Рузультат вычисления функции
 * @param <T>
 */
public interface IFunctionResult<T> {

    /**
     * Рузультат вычисления
     * @return результат
     */
    T getResult();

    /**
     * Список ошибок в результате вычисления
     * @return список ошибок
     */
    List<IFunctionError> getErrors();
}
