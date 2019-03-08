package ru.dstu.railway.area;

import ru.dstu.railway.element.IStationElement;

import java.util.Set;

/**
 *  Интерфейс области
 */
public interface IArea {

    /**
     * Добавить объект в область
     * @param stationElement станционный объект
     */
    void addElement(IStationElement stationElement);

    /**
     * Список объектов области
     * @return набор объектов
     */
    Set<IStationElement> getElements();

    /**
     * Получить код области
     * @return код области
     */
    String getAreaCode();
}
