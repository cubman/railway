package ru.dstu.railway.api.state;

import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;

@FunctionalInterface
public interface IStateSender {

    /**
     * Инициировать установку нового состояния
     * @param area область
     * @param stationElement объект, котрому устанавливается внешнее состояние
     * @param state новое состояние
     */
    void sendState(IArea area, IStationElement stationElement, int state);
}
