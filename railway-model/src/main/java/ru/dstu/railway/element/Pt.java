package ru.dstu.railway.element;

/**
 * Объект путь
 */
public class Pt extends AbstractElement {

    private IStationElement even;
    private IStationElement odd;

    @Override
    public IStationElement getEven() {
        return even;
    }

    @Override
    public IStationElement getOdd() {
        return odd;
    }

    public void setEven(IStationElement even) {
        this.even = even;
    }

    public void setOdd(IStationElement odd) {
        this.odd = odd;
    }
}
