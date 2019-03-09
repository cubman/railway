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
        throw new UnsupportedOperationException("getEven");
    }

    @Override
    public IStationElement getOdd() {
        throw new UnsupportedOperationException("getOdd");
    }
}
