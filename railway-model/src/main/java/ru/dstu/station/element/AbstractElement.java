package ru.dstu.station.element;

import ru.dstu.station.state.IState;
import ru.dstu.station.state.State;

import java.util.Date;

public abstract class AbstractElement implements IStationElement {

    protected String code;
    protected State state;

    public AbstractElement(String code) {
        this.code = code;
        this.state = new State(new Date(), 0);
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
}
