package ru.dstu.railway.api.polygon;

import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;

import java.util.List;

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

    /**
     * Получить спсисок всех областей на полигоне
     * @return список областей
     */
    List<IArea> getAreas();
}
