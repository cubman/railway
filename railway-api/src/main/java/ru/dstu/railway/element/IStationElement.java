package ru.dstu.railway.element;

import ru.dstu.railway.state.IState;

/**
 * Интерфейс объекта полигона
 */
public interface IStationElement {
    /**
     * Получить код объекта (уникальный в области)
     * @return код объекта
     */
    String getCode();

    /**
     * Получить объект в четном направлении
     * @return объект
     */
    IStationElement getEven();

    /**
     * Получить объект в нечетном направлении
     * @return объект
     */
    IStationElement getOdd();

    /**
     * Установить состяние объекту в виде числового значения
     * @param state состояние
     */
    void setState(int state);

    /**
     * Получить состояние объекта
     * @return состояние
     */
    IState getState();
}
