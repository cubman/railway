package ru.dstu.railway.element;

/**
 * Объект путь
 */
public class Kp extends AbstractElement {

    public Kp(String code) {
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
