package ru.dstu.railway.paint.draw.drawer;

import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.paint.IDrawElement;
import ru.dstu.railway.api.figure.IFigure;
import ru.dstu.railway.api.state.IState;

import java.util.List;

import static ru.dstu.railway.api.constant.Constant.*;
import static ru.dstu.railway.paint.draw.drawer.BaseDraw.NON_STATE_COLOR;
import static ru.dstu.railway.paint.draw.drawer.BaseDraw.RED_COLOR;

public class DrawUp implements IDrawElement {

    private IStationElement element;

    public DrawUp(IStationElement element) {
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
    public void drawFigures(List<IFigure> figures) {
        IState state = element.getState();

        switch (state.getState()) {
            case UP_NON:
                ifNon(figures);
                break;
            case UP_NOT_BUSY:
                ifNotBusy(figures);
                break;
            case UP_BUSY:
                ifBusy(figures);
                break;
            default:
                throw new UnsupportedOperationException("Состояние не известно для участка приближения/ удаления: " + state);
        }
    }
}

