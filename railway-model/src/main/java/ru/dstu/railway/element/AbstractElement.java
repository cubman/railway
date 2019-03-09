package ru.dstu.railway.element;

import ru.dstu.railway.area.IArea;
import ru.dstu.railway.state.IState;
import ru.dstu.railway.state.State;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractElement implements IStationElement {

    protected String elementCode;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{elementCode='" + elementCode + '\'' +
                '}';
    }

    protected final State state;
    protected final List<IArea> areas;

    public AbstractElement() {
        this.state = new State(new Date(), 0);
        this.areas = new ArrayList<>();
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    @Override
    public String getElementCode() {
        return elementCode;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AbstractElement that = (AbstractElement) obj;

        return elementCode != null ? elementCode.equals(that.elementCode) : that.elementCode == null;
    }

    @Override
    public int hashCode() {
        return elementCode != null ? elementCode.hashCode() : 0;
    }
}
