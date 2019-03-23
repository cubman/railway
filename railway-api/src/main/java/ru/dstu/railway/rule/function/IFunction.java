package ru.dstu.railway.rule.function;

/**
 * Интерфейс функции
 */
@FunctionalInterface
public interface IFunction {

    /**
     * Проверить условие функции
     * @return результат проверки
     */
    IFunctionResult<Boolean> check();
}
