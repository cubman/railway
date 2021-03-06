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

    private void ifBusy(List<IFigure> figures) {
        IFigure figure = figureById(figures, 5);
        if (TRANSPARENT_COLOR.equals(figure.getColor())) {
            BaseDraw.setColorsById(figures, RED_COLOR, 1, 4, 7);
        } else {
            BaseDraw.setColorsById(figures, RED_COLOR, 1, 6);
        }
    }

    private void ifNotBusy(List<IFigure> figures) {
        BaseDraw.setColorsById(figures, BASE_COLOR, 1, 4, 6, 7);
    }

    private void ifPlus(List<IFigure> figures) {
        BaseDraw.setBaseColors(figures);

        if (BaseDraw.figureById(figures, 5).isPlus()) {
            BaseDraw.setColorsById(figures, YELLOW_COLOR, 2, 5);
            BaseDraw.setColorsById(figures, TRANSPARENT_COLOR, 3);
        } else if (BaseDraw.figureById(figures, 3).isPlus()) {
            BaseDraw.setColorsById(figures, YELLOW_COLOR, 2, 3);
            BaseDraw.setColorsById(figures, TRANSPARENT_COLOR, 5);
        } else {
            throw new UnsupportedOperationException(element + " не имеет однозначного определения положения стрелки");
        }
    }

    private void ifMinus(List<IFigure> figures) {
        BaseDraw.setBaseColors(figures);

        if (BaseDraw.figureById(figures, 5).isPlus()) {
            BaseDraw.setColorsById(figures, YELLOW_COLOR, 2, 3);
            BaseDraw.setColorsById(figures, TRANSPARENT_COLOR, 5);
        } else if (BaseDraw.figureById(figures, 3).isPlus()) {
            BaseDraw.setColorsById(figures, YELLOW_COLOR, 2, 5);
            BaseDraw.setColorsById(figures, TRANSPARENT_COLOR, 3);
        } else {
            throw new UnsupportedOperationException(element + " не имеет однозначного определения положения стрелки");
        }
    }

    private static void ifNon(List<IFigure> figures) {
        BaseDraw.setColors(figures, NON_STATE_COLOR);
    }

    @Override
    public void drawFigures(List<IFigure> figures) {
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
                ifBusy(figures);
                break;
            case ST_NOT_BUSY:
                ifNotBusy(figures);
                break;
            default:
                throw new UnsupportedOperationException("Состояние не известно для стрелки: " + state);
        }
    }
}
