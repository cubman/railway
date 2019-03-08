package ru.dstu.railway.area;

import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.exception.DuplicationException;

import java.util.HashSet;
import java.util.Set;


public class AbstractArea implements IArea {
    protected final Set<IStationElement> areaElements;
    protected final String areaCode;

    public AbstractArea(String areaCode) {
        this.areaElements = new HashSet<>();
        this.areaCode = areaCode;
    }

    @Override
    public void addElement(IStationElement stationElement) {
        if (areaElements.add(stationElement)) {
            throw new DuplicationException(stationElement.getElementCode() + " уже существует в области " + areaCode);
        }
    }

    @Override
    public Set<IStationElement> getElements() {
        return areaElements;
    }

    @Override
    public String getAreaCode() {
        return null;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{areaCode='" + areaCode + '\'' +
                '}';
    }
}
