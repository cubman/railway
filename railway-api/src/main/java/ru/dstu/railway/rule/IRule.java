package ru.dstu.railway.rule;

/**
 * Правило (условие стабильности)
 */
public interface IRule {

    /**
     * Наименование правила
     * @return название правила
     */
    String getName();

    /**
     * проверить, что система стабильна
     * @return результат проверки
     */
    boolean check();

    /**
     * Исполнить процесс
     */
    void execute();
}
