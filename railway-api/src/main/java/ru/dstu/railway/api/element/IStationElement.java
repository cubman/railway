package ru.dstu.railway.api.element;

import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.rule.IRule;
import ru.dstu.railway.api.state.IState;

import java.util.List;

/**
 * Интерфейс объекта полигона
 */
public interface IStationElement {
    /**
     * Получить код объекта (уникальный в области)
     * @return код объекта
     */
    String getElementCode();

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
     * @param area область, инициирующая выставление состояния
     * @param state состояние
     */
    void setState(IArea area, int state);

    /**
     * Получить состояние объекта
     * @return состояние
     */
    IState getState();

    /**
     * Добавить область объекту
     * @param area область
     */
    void addArea(IArea area);

    /**
     * Получить список областей, в которых присутствует объект
     * @return
     */
    List<IArea> getAreas();
}
