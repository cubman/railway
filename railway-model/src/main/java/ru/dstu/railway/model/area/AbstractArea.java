package ru.dstu.railway.model.area;

import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.model.element.St;
import ru.dstu.railway.model.element.Up;
import ru.dstu.railway.model.exception.DuplicationException;

import java.util.*;
import java.util.stream.Collectors;


public abstract class AbstractArea implements IArea {
    private final Map<String, IStationElement> areaElements;
    private final String areaCode;
    private final String esr;
    private final Map<IStationElement, List<IStationElement>> changeParties;

    AbstractArea(String areaCode, String esr) {
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

    public String getEsr() {
        return esr;
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
    public List<IStationElement> getElementsByType(String typeCode) {
        Class<? extends IStationElement> stationElementClass;
        switch (typeCode.toUpperCase()) {
            case "ST":
                stationElementClass = St.class;
                break;
            case "UP":
                stationElementClass = Up.class;
                break;
            default:
                throw new IllegalArgumentException(typeCode + " не распознан");
        }
        return getElementsByType(stationElementClass);
    }

    @Override
    public List<IStationElement> getElementsByType(Class<? extends IStationElement> type) {
        return areaElements.values().stream().
                filter(stationElement -> type.isAssignableFrom(stationElement.getClass()))
                .collect(Collectors.toList());
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

        return areaCode.equals(that.areaCode)
                && esr.equals(that.esr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, esr);
    }

    @Override
    public String toString() {
        return getClass() +
                "{areaCode='" + areaCode + '\'' +
                ", esr='" + esr + '\'' +
                '}';
    }
}
