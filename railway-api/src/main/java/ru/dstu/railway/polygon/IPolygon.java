package ru.dstu.railway.polygon;

import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;

public interface IPolygon {
    /**
     * Добавить новую  область в полигон
     * @param area новая область
     */
    void addArea(IArea area);

    /**
     * Получить область по коду в полигоне
     * @param code код лобласти
     * @return область
     */
    IArea getAreaByCode(String code);

    /**
     * Получить объектт по коду области и коду объекта в этой области
     * @param areaCode код области
     * @param elementCode код объекта
     * @return объект
     */
    IStationElement getElementByAreaAndCode(String areaCode, String elementCode);
}