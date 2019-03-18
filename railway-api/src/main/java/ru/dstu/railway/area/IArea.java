package ru.dstu.railway.area;

import ru.dstu.railway.element.IStationElement;

import java.util.List;
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

    /**
     * Получить код единой сетевой разметки
     * @return код ЕСР
     */
    String getEsr();

    /**
     * Список объектов по коду типу
     * @param typeCode код типа объекта
     * @return список объектов
     */
    List<IStationElement> getElementsByType(String typeCode);

    /**
     * Список объектов по типу
     * @param type тип объекта
     * @return список объектов
     */
    List<IStationElement> getElementsByType(Class<? extends IStationElement> type);

    /**
     * Меняется ли четность при переходе из первого объекта к другому
     * @param firstElement перевый элемент
     * @param secondElement второй элемент
     * @return четность меняется
     */
    boolean isPartyChanges(IStationElement firstElement, IStationElement secondElement);
}
