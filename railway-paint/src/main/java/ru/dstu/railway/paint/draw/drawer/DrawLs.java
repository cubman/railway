package ru.dstu.railway.paint.draw.drawer;

import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.paint.IDrawElement;
import ru.dstu.railway.paint.figure.IFigure;
import ru.dstu.railway.state.IState;

import java.util.List;

import static ru.dstu.railway.constant.Constant.*;
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
