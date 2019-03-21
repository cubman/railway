package ru.dstu.railway.logcon.struct;

import ru.dstu.railway.paint.figure.IFigure;
import ru.dstu.railway.paint.figure.Line;
import ru.dstu.railway.state.IState;

import java.util.List;

public class JElement {
    private String code;
    private IState state;
    private String type;

    private JNextElement even;
    private JNextElement odd;

    private List<JOwnArea> ownAreas;

    private List<IFigure> figures;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JNextElement getEven() {
        return even;
    }

    public void setEven(JNextElement even) {
        this.even = even;
    }

    public JNextElement getOdd() {
        return odd;
    }

    public void setOdd(JNextElement odd) {
        this.odd = odd;
    }

    public List<JOwnArea> getOwnAreas() {
        return ownAreas;
    }

    public void setOwnAreas(List<JOwnArea> ownAreas) {
        this.ownAreas = ownAreas;
    }

    public List<IFigure> getFigures() {
        return figures;
    }

    public void setFigures(List<IFigure> figures) {
        this.figures = figures;
    }
}
