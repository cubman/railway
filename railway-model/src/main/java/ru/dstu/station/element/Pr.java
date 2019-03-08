package ru.dstu.station.element;

/**
 * Объект переезд
 */
public class Pr extends AbstractElement {

    public Pr(String code) {
        super(code);
    }

    @Override
    public IStationElement getEven() {
        return null;
    }

    @Override
    public IStationElement getOdd() {
        return null;
    }
}
