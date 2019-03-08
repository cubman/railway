package ru.dstu.railway.state;

import java.util.Date;

/**
 * Интерфейс состояния объекта
 */
public interface IState {
    /**
     * Последнее изменение состояния
     * @return дата изменения
     */
    public Date getLastChange();

    /**
     * Получить состояние объекта
     * @return состояние
     */
    public int getState();
}
