package ru.dstu.railway.model.element;

import ru.dstu.railway.api.element.IStationElement;

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
