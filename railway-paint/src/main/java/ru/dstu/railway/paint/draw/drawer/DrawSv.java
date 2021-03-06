package ru.dstu.railway.paint.draw.drawer;

import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.paint.IDrawElement;
import ru.dstu.railway.api.figure.IFigure;
import ru.dstu.railway.api.state.IState;

import java.util.List;

import static ru.dstu.railway.api.constant.Constant.*;
import static ru.dstu.railway.paint.draw.drawer.BaseDraw.*;

public class DrawSv implements IDrawElement {

    private IStationElement element;

    public DrawSv(IStationElement element) {
        this.element = element;
    }

    private void ifClosed(List<IFigure> figures) {
        BaseDraw.setBaseColors(figures);
        BaseDraw.setColorsById(figures, RED_COLOR, 4, 5, 6, 7);
    }

    private void ifOpen(List<IFigure> figures) {
        BaseDraw.setBaseColors(figures);
        BaseDraw.setColorsById(figures, GREEN_COLOR, 4, 5, 6, 7);
    }

    private void ifNon(List<IFigure> figures) {
        BaseDraw.setColors(figures, NON_STATE_COLOR);
    }

    @Override
    public void drawFigures(List<IFigure> figures) {
        IState state = element.getState();

        switch (state.getState()) {
            case SV_NON:
                ifNon(figures);
                break;
            case SV_OPEN:
                ifOpen(figures);
                break;
            case SV_CLOSED:
                ifClosed(figures);
                break;
            default:
                throw new UnsupportedOperationException("Состояние не известно для стрелки: " + state);
        }
    }
}
