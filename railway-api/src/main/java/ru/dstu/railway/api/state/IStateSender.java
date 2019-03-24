package ru.dstu.railway.api.state;

import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;

public interface IStateSender {

    void sendState(IArea area, IStationElement stationElement, int state);
}
