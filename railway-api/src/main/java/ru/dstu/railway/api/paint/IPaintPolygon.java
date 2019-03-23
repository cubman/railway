package ru.dstu.railway.api.paint;

import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.figure.IFigure;

import java.util.List;

public interface IPaintPolygon {

    List<IFigure> getElementFigures(IArea area, IStationElement element);

    void setColors(IArea area, IStationElement element);

    void refresh();
}
