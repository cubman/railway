package ru.dstu.railway.paint.draw.drawer;

import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.paint.IDrawElement;
import ru.dstu.railway.paint.figure.IFigure;
import ru.dstu.railway.state.IState;

import java.util.List;

import static ru.dstu.railway.constant.Constant.*;
import static ru.dstu.railway.paint.draw.drawer.BaseDraw.NON_STATE_COLOR;
import static ru.dstu.railway.paint.draw.drawer.BaseDraw.RED_COLOR;

public class DrawKp implements IDrawElement {

    private IStationElement element;

    public DrawKp(IStationElement element) {
        this.element = element;
    }

    private void ifNotBusy(List<IFigure> figures) {
        BaseDraw.setBaseColors(figures);
    }

    private void ifBusy(List<IFigure> figures) {
        BaseDraw.setColorsById(figures, RED_COLOR, 1);
    }

    private void ifNon(List<IFigure> figures) {
        BaseDraw.setColors(figures, NON_STATE_COLOR);
    }

    @Override
    public void drawColors(List<IFigure> figures) {
        IState state = element.getState();

        switch (state.getState()) {
            case KP_NON:
                ifNon(figures);
                break;
            case KP_NOT_BUSY:
                ifNotBusy(figures);
                break;
            case KP_BUSY:
                ifBusy(figures);
                break;
            default:
                throw new UnsupportedOperationException("Состояние не известно для стрелки: " + state);
        }
    }
}
