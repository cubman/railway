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

public abstract class AbstractElement implements IStationElement {

    private String elementCode;
//    private final List<IRule> rules;
    protected final State state;
    private final List<IArea> areas;
    private final List<IStateListener> stateListeners;

    public AbstractElement() {
        this.state = new State(new Date(), 1);
        this.areas = new ArrayList<>();
//        this.rules = new ArrayList<>();
        this.stateListeners = new ArrayList<>();
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

//    public void addRule(IRule rule) {
//        rules.add(rule);
//    }

    public void addStateListener(IStateListener stateListener) {
        stateListeners.add(stateListener);
    }


    @Override
    public String getElementCode() {
        return elementCode;
    }

    @Override
    public void setState(int state) {
        this.state.setState(state);
        this.state.setLastChange(new Date());

        stateListeners.forEach(stateListener -> stateListener.notify(areas.get(0), this));
//        List<IRule> checked = rules.stream().filter(IRule::check).collect(Collectors.toList());
//        if (!checked.isEmpty()) {
//            сheckedRuleListeners.forEach(iCheckedRuleListener -> iCheckedRuleListener.notify(checked));
//        }
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
    public List<IRule> getUncheckedRules() {
        return null;
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
