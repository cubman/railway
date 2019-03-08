package ru.dstu.station.element;

/**
 * Участок приближения/ удаления
 */
public class Up extends AbstractElement {

    public Up(String code) {
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
