package ru.dstu.railway.element;

import ru.dstu.railway.area.IArea;
import ru.dstu.railway.state.IState;
import ru.dstu.railway.state.State;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractElement implements IStationElement {

    protected String code;
    protected State state;
    protected List<IArea> areas;

    public AbstractElement(String code) {
        this.code = code;
        this.state = new State(new Date(), 0);
        this.areas = new ArrayList<>();
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setState(int state) {
        this.state.setState(state);
        this.state.setLastChange(new Date());
    }

    @Override
    public IState getState() {
        return state;
    }

    @Override
    public void addArea(IArea area) {
        areas.add(area);
    }

    @Override
    public List<IArea> getAreas() {
        return areas;
    }
}
