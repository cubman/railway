package ru.dstu.railway.state;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс состояние объекта,
 */
public class State implements IState {
    private Date lastChange;
    private AtomicInteger state;

    public State(Date lastChange, int state) {
        this.lastChange = lastChange;
        this.state = new AtomicInteger(state);
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public int getState() {
        return state.get();
    }

    public void setState(int state) {
        this.state.set(state);
    }
}
