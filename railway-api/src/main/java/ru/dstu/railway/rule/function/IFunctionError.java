package ru.dstu.railway.rule.function;

/**
 * Интерфейс описания ошибки
 */
public interface IFunctionError {
    /**
     * Код ошибки
     * @return код
     */
    String getCode();

    /**
     * Описание ошибки
     * @return описание
     */
    String getDescription();
}
