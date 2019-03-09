package ru.dstu.railway.rule;

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
     * Исполнить процесс, если результат check = false
     */
    void execute();
}
