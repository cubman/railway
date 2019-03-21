package ru.dstu.railway.element;

import java.util.List;

/**
 * Объект стрелка
 */
public class St extends AbstractElement {
    private List<IStationElement> evenElement;
    private List<IStationElement> oddElement;

    @Override
    public IStationElement getEven() {
        return getElement( evenElement);
    }

    @Override
    public IStationElement getOdd() {
        return getElement(oddElement);
    }

    private IStationElement getElement(List<IStationElement> elements) {
        switch (state.getState()) {
            case 1:
                return elements.get(0);
            default:
                return elements.get(1);
//            default:
//                throw new UnknownStateCodeException("Неизвестный код: " + state.getState());
        }
    }

    public void setEvenElement(List<IStationElement> evenElement) {
        this.evenElement = evenElement;
    }

    public void setOddElement(List<IStationElement> oddElement) {
        this.oddElement = oddElement;
    }
}
