package ru.dstu.railway.paint.draw.drawer;

import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.paint.IDrawElement;
import ru.dstu.railway.api.figure.IFigure;
import ru.dstu.railway.paint.figure.MrLabel;
import ru.dstu.railway.api.state.IState;

import java.util.List;

import static ru.dstu.railway.api.constant.Constant.*;
import static ru.dstu.railway.paint.draw.drawer.BaseDraw.*;

public class DrawMr implements IDrawElement {

    private IStationElement element;

    public DrawMr(IStationElement element) {
        this.element = element;
    }

    @Override
    public void drawColors(List<IFigure> figures) {
        IState state = element.getState();

        switch (state.getState()) {
            case MR_NON:
                ifNon(figures);
                break;
            case MR_STATE_1:
                ifState1(figures);
                break;
            case MR_STATE_2:
                ifState2(figures);
                break;
            case MR_STATE_3:
                ifState3(figures);
                break;
            case MR_STATE_4:
                ifState4(figures);
                break;
            default:
                throw new UnsupportedOperationException("Состояние не известно для участка приближения/ удаления: " + state);
        }
    }

    private void ifNon(List<IFigure> figures) {
        setMrColors(figures, BASE_COLOR, NON_STATE_COLOR);
    }

    private void ifState1(List<IFigure> figures) {
        setMrColors(figures, BASE_COLOR, YELLOW_COLOR);
    }

    private void ifState2(List<IFigure> figures) {
        setMrColors(figures, BASE_COLOR, RED_COLOR);
    }

    private void ifState3(List<IFigure> figures) {
        setMrColors(figures, RED_COLOR, BASE_COLOR);
    }

    private void ifState4(List<IFigure> figures) {
        setMrColors(figures, YELLOW_COLOR, NON_STATE_COLOR);
    }

    private void setMrColors(List<IFigure> figures, String textColor, String borderColor) {
        figures.forEach(iFigure -> {
            MrLabel mrLabel = (MrLabel) iFigure;
            mrLabel.setTextColor(textColor);
            mrLabel.setBorderColor(borderColor);
        });
    }
}

