package ru.dstu.railway.paint.draw.drawer;

import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.paint.IDrawElement;
import ru.dstu.railway.api.figure.IFigure;
import ru.dstu.railway.api.state.IState;

import java.util.List;

import static ru.dstu.railway.api.constant.Constant.*;
import static ru.dstu.railway.paint.draw.drawer.BaseDraw.*;

public class DrawSt implements IDrawElement {
    private IStationElement element;

    public DrawSt(IStationElement element) {
        this.element = element;
    }

    private void ifBusy(IStationElement element, List<IFigure> figures) {
        BaseDraw.setColorsById(figures, RED_COLOR, 1, 4, 6, 7);
    }

    private void ifNotBusy(IStationElement element, List<IFigure> figures) {
        BaseDraw.setColorsById(figures, BASE_COLOR, 1, 4, 6, 7);
    }

    private void ifPlus(List<IFigure> figures) {
        BaseDraw.setBaseColors(figures);
        BaseDraw.setColorsById(figures, TRANSPARENT_COLOR, 3);
        BaseDraw.setColorsById(figures, YELLOW_COLOR, 2, 5);
    }

    private void ifMinus(List<IFigure> figures) {
        BaseDraw.setBaseColors(figures);
        BaseDraw.setColorsById(figures, TRANSPARENT_COLOR, 5);
        BaseDraw.setColorsById(figures, YELLOW_COLOR, 2, 3);
    }

    private static void ifNon(List<IFigure> figures) {
        BaseDraw.setColors(figures, NON_STATE_COLOR);
    }

    @Override
    public void drawColors(List<IFigure> figures) {
        IState state = element.getState();

        switch (state.getState()) {
            case ST_NON:
                ifNon(figures);
                break;
            case ST_PLUS:
                ifPlus(figures);
                break;
            case ST_MINUS:
                ifMinus(figures);
                break;
            case ST_BUSY:
                ifBusy(element, figures);
                break;
            case ST_NOT_BUSY:
                ifNotBusy(element, figures);
                break;
            default:
                throw new UnsupportedOperationException("Состояние не известно для стрелки: " + state);
        }
    }
}
