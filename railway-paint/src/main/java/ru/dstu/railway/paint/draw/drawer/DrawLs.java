package ru.dstu.railway.paint.draw.drawer;

import ru.dstu.railway.api.paint.IDrawElement;
import ru.dstu.railway.api.figure.IFigure;

import java.util.List;

import static ru.dstu.railway.paint.draw.drawer.BaseDraw.NON_STATE_COLOR;
import static ru.dstu.railway.paint.draw.drawer.BaseDraw.RED_COLOR;

public class DrawLs implements IDrawElement {

    private void ifNotBusy(List<IFigure> figures) {
        BaseDraw.setBaseColors(figures);
    }

    private void ifBusy(List<IFigure> figures) {
        BaseDraw.setColorsById(figures, RED_COLOR, 1, 2);
    }

    private void ifNon(List<IFigure> figures) {
        BaseDraw.setColors(figures, NON_STATE_COLOR);
    }

    @Override
    public void drawColors(List<IFigure> figures) {

    }
}
