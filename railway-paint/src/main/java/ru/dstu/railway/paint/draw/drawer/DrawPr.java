package ru.dstu.railway.paint.draw.drawer;

import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.paint.IDrawElement;
import ru.dstu.railway.paint.figure.IFigure;
import ru.dstu.railway.state.IState;

import java.util.List;

import static ru.dstu.railway.constant.Constant.*;
import static ru.dstu.railway.paint.draw.drawer.BaseDraw.NON_STATE_COLOR;
import static ru.dstu.railway.paint.draw.drawer.BaseDraw.RED_COLOR;

public class DrawPr implements IDrawElement {

    private IStationElement element;

    public DrawPr(IStationElement element) {
        this.element = element;
    }

    private void ifNotBusy(List<IFigure> figures) {
        BaseDraw.setBaseColors(figures);
    }

    private void ifBusy(List<IFigure> figures) {
        BaseDraw.setColors(figures, RED_COLOR);
    }

    private void ifNon(List<IFigure> figures) {
        BaseDraw.setColors(figures, NON_STATE_COLOR);
    }

    @Override
    public void drawColors(List<IFigure> figures) {
        IState state = element.getState();

        switch (state.getState()) {
            case PR_NON:
                ifNon(figures);
                break;
            case PR_NOT_BUSY:
                ifNotBusy(figures);
                break;
            case PR_BUSY:
                ifBusy(figures);
                break;
            default:
                throw new UnsupportedOperationException("Состояние не известно для участка приближения/ удаления: " + state);
        }
    }
}

