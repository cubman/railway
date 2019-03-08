package ru.dstu.railway.state;

import java.util.Date;

/**
 * Класс состояние объекта,
 */
public class State implements IState {
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
