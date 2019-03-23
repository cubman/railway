package ru.dstu.railway.model.element;

import ru.dstu.railway.api.element.IStationElement;

import java.util.List;

import static ru.dstu.railway.api.constant.Constant.ST_PLUS;

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
        if (state.getState() == ST_PLUS) {
            return elements.get(0);
        }
        return elements.get(1);
//            default:
//                throw new UnknownStateCodeException("Неизвестный код: " + state.getState());
    }

    public void setEvenElement(List<IStationElement> evenElement) {
        this.evenElement = evenElement;
    }

    public void setOddElement(List<IStationElement> oddElement) {
        this.oddElement = oddElement;
    }
}
