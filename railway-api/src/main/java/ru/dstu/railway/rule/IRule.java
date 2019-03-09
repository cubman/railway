package ru.dstu.railway.rule;

import ru.dstu.railway.rule.function.IFunction;

import java.util.function.Consumer;

/**
 * Правило (условие стабильности)
 */
public interface IRule {
    /**
     * проверить, что система стабильна
     * @return результат проверки
     */
    boolean check();

    /**
     * Исполнить процесс
     * @param executor обработчик условия проверки
     */
    void execute(Consumer<IFunction> executor);
}
