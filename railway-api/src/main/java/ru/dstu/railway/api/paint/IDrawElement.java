package ru.dstu.railway.api.paint;

import ru.dstu.railway.api.figure.IFigure;

import java.util.List;

@FunctionalInterface
public interface IDrawElement {

    void drawFigures(List<IFigure> figures);
}
