package ru.dstu.railway.api.paint;

import ru.dstu.railway.api.figure.IFigure;

import java.util.List;

public interface IDrawElement {

    void drawColors(List<IFigure> figures);
}
