package ru.dstu.railway.model.element;

import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.listener.IStateListener;
import ru.dstu.railway.api.rule.IRule;
import ru.dstu.railway.api.state.IState;
import ru.dstu.railway.model.state.State;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractElement implements IStationElement {

    private static final Logger LOGGER = Logger.getLogger(AbstractElement.class.getName());

    private String elementCode;
    protected final State state;
    private final List<IArea> areas;
    private final List<IStateListener> stateListeners;

    public AbstractElement() {
        this.state = new State(new Date(), 1);
        this.areas = new ArrayList<>();
        this.stateListeners = new ArrayList<>();
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public void addStateListener(IStateListener stateListener) {
        stateListeners.add(stateListener);
    }


    @Override
    public String getElementCode() {
        return elementCode;
    }

    @Override
    public void setState(IArea area, int state) {
        if (!areas.contains(area)) {
            throw new UnsupportedOperationException(area + " не принадлежит объекту" + this);
        }

        LOGGER.info(String.format("Выставление состояния: объект %s, область %s, состояние %s", this, area, state));

        this.state.setState(state);
        this.state.setLastChange(new Date());

        stateListeners.forEach(stateListener -> stateListener.notify(area, this));
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

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{elementCode='" + elementCode + '\'' +
                '}';
    }
}
