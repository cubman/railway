package ru.dstu.railway.rule.function;

/**
 * Интерфейс функции
 */
public interface IFunction {

    /**
     * Проверить условие функции
     * @return результат проверки
     */
    IFunctionResult<Boolean> check();
}
