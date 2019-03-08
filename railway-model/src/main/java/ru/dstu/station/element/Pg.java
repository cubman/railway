package ru.dstu.station.element;

/**
 * Объект перегон
 */
public class Pg extends AbstractElement {

    public Pg(String code) {
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