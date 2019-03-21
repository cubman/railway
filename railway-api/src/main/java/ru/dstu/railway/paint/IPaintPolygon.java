package ru.dstu.railway.paint;

import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.paint.figure.IFigure;

import java.util.List;

public interface IPaintPolygon {

    List<IFigure> getElementFigures(IArea area, IStationElement element);
}
