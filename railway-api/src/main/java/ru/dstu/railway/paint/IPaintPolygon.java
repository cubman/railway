package ru.dstu.railway.paint;

import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;

import java.util.List;

public interface IPaintPolygon {

    List<Line> getElementLines(IArea area, IStationElement element);
}
