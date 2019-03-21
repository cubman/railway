package ru.dstu.railway.paint.draw;

import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.paint.figure.IFigure;
import ru.dstu.railway.state.IState;

import java.util.List;

import static ru.dstu.railway.paint.parse.PaintPolygon.NON_STATE_COLOR;
import static ru.dstu.railway.paint.parse.PaintPolygon.TRANSPARENT_COLOR;

public class DrawSt {

    public static void updateColors(IStationElement element, List<IFigure> figures) {
        IState state = element.getState();

        switch (state.getState()) {
            case 0:
                ifNon(figures);
                break;
            case 1:
                ifPlus(figures);
                break;
            case 2:
                ifMinus(figures);
                break;
            default:
                throw new UnsupportedOperationException("Состояние не известно для стрелки: " + state);
        }
    }

    private static void ifPlus(List<IFigure> figures) {
        BaseDraw.setBaseColors(figures);
        BaseDraw.setColorsById(figures, 3, TRANSPARENT_COLOR);
    }

    private static void ifMinus(List<IFigure> figures) {
        BaseDraw.setBaseColors(figures);
        BaseDraw.setColorsById(figures, 5, TRANSPARENT_COLOR);
    }

    private static void ifNon(List<IFigure> figures) {
        BaseDraw.setColors(figures, NON_STATE_COLOR);
    }
}
