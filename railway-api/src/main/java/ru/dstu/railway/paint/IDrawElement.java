package ru.dstu.railway.paint;

import ru.dstu.railway.paint.figure.IFigure;

import java.util.List;

public interface IDrawElement {

    void drawColors(List<IFigure> figures);
}
