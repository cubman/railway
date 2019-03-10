package ru.dstu.railway.area;

import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.exception.DuplicationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public abstract class AbstractArea implements IArea {
    protected final Map<String, IStationElement> areaElements;
    protected final String areaCode;
    protected final String esr;
    private final Map<IStationElement, List<IStationElement>> changeParties;

    public AbstractArea(String areaCode, String esr) {
        this.areaElements = new HashMap<>();
        this.areaCode = areaCode;
        this.esr = esr;

        this.changeParties = new HashMap<>();
    }

    public void addChangeParty(IStationElement from, IStationElement to) {
        List<IStationElement> elements = changeParties.putIfAbsent(from, new ArrayList<>());

        if (elements == null) {
            elements = changeParties.get(from);
        }

        elements.add(to);
    }

    @Override
    public void addElement(IStationElement stationElement) {
        if (areaElements.putIfAbsent(stationElement.getElementCode(), stationElement) != null) {
            throw new DuplicationException(stationElement.getElementCode() + " уже существует в области " + areaCode);
        }
    }

    @Override
    public Map<String, IStationElement> getElements() {
        return areaElements;
    }

    @Override
    public IStationElement getElementByCode(String code) {
        return areaElements.get(code);
    }

    @Override
    public String getAreaCode() {
        return areaCode;
    }

    @Override
    public List<IStationElement> getElementsByType(Class<? extends IStationElement> type) {
        return areaElements.values().stream().
                filter(stationElement -> type.isAssignableFrom(stationElement.getClass())).collect(Collectors.toList());
    }

    @Override
    public boolean isPartyChanges(IStationElement firstElement, IStationElement secondElement) {
        List<IStationElement> elements = changeParties.get(firstElement);
        return elements != null && elements.contains(secondElement);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AbstractArea that = (AbstractArea) obj;

        return areaCode.equals(that.areaCode);
    }

    @Override
    public int hashCode() {
        return areaCode.hashCode();
    }

    @Override
    public String toString() {
        return getClass() +
                "{areaCode='" + areaCode + '\'' +
                ", esr='" + esr + '\'' +
                '}';
    }
}
