package ru.dstu.railway.element;

/**
 * Крайний объект
 */
public class Ls extends AbstractElement {

    public Ls(String elementCode) {
        super();

        setElementCode(elementCode);
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
