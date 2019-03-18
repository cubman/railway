package ru.dstu.railway.element;

import ru.dstu.railway.area.IArea;
import ru.dstu.railway.rule.IRule;
import ru.dstu.railway.listener.ICheckedRuleListener;
import ru.dstu.railway.state.IState;
import ru.dstu.railway.state.State;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractElement implements IStationElement {

    private String elementCode;
    private final List<IRule> rules;
    protected final State state;
    private final List<IArea> areas;
    private final List<ICheckedRuleListener> сheckedRuleListeners;

    public AbstractElement() {
        this.state = new State(new Date(), 0);
        this.areas = new ArrayList<>();
        this.rules = new ArrayList<>();
        this.сheckedRuleListeners = new ArrayList<>();
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public void addRule(IRule rule) {
        rules.add(rule);
    }

    public void addCheckRuleListener(ICheckedRuleListener ruleListener) {
        сheckedRuleListeners.add(ruleListener);
    }


    @Override
    public String getElementCode() {
        return elementCode;
    }

    @Override
    public void setState(int state) {
        this.state.setState(state);
        this.state.setLastChange(new Date());

        List<IRule> checked = rules.stream().filter(IRule::check).collect(Collectors.toList());
        if (!checked.isEmpty()) {
            сheckedRuleListeners.forEach(iCheckedRuleListener -> iCheckedRuleListener.notify(checked));
        }
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
