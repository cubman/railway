package ru.dstu.railway.element;

/**
 * Объект светофор
 */
public class Sv extends AbstractElement {

    public Sv(String code) {
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
