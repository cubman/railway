package ru.dstu.railway.model.element;

import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;

import java.util.List;

import static ru.dstu.railway.api.constant.Constant.ST_MINUS;
import static ru.dstu.railway.api.constant.Constant.ST_PLUS;

/**
 * Объект стрелка
 */
public class St extends AbstractElement {
    private List<IStationElement> evenElement;
    private List<IStationElement> oddElement;
    private boolean isPlus;

    public St() {
        isPlus = state.getState() == ST_PLUS;
    }

    @Override
    public IStationElement getEven() {
        return getElement(evenElement);
    }

    @Override
    public IStationElement getOdd() {
        return getElement(oddElement);
    }

    private IStationElement getElement(List<IStationElement> elements) {
        return isPlus ? elements.get(0) : elements.get(1);
//            default:
//                throw new UnknownStateCodeException("Неизвестный код: " + state.getState());
    }

    @Override
    public void setState(IArea area, int state) {
        if (state == ST_PLUS || state == ST_MINUS) {
            isPlus = state == ST_PLUS;
        }

        super.setState(area, state);
    }

    public void setEvenElement(List<IStationElement> evenElement) {
        this.evenElement = evenElement;
    }

    public void setOddElement(List<IStationElement> oddElement) {
        this.oddElement = oddElement;
    }
}
