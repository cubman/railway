package ru.dstu.station.element;

/**
 * Объект маркер (шильдик)
 */
public class Mr extends AbstractElement {

    public Mr(String code) {
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