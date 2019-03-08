package ru.dstu.railway.area;

import ru.dstu.railway.element.IStationElement;

import java.util.Map;

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
    Map<String, IStationElement> getElements();

    /**
     * Получить объект области по коду
     * @param code код элемента
     * @return объект
     */
    IStationElement getElementByCode(String code);

    /**
     * Получить код области
     * @return код области
     */
    String getAreaCode();
}
