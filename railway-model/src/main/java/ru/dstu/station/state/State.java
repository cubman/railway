package ru.dstu.station.state;

import java.util.Date;

public class State {
    private Date lastChange;
    private int state;

    public State(Date lastChange, int state) {
        this.lastChange = lastChange;
        this.state = state;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
