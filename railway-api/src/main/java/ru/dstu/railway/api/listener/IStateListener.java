package ru.dstu.railway.api.listener;

import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;

public interface IStateListener {
    /**
     * Уведомить о новом сообщении
     */
    void notify(IArea area, IStationElement element);
}
