package ru.dstu.railway.element;

import ru.dstu.railway.exception.UnknownStateCodeException;
import ru.dstu.railway.state.Direction;

import java.util.List;

import static ru.dstu.railway.constant.CodeConstant.ST_MINUS;
import static ru.dstu.railway.constant.CodeConstant.ST_PLUS;

/**
 * Объект стрелка
 */
public class St extends AbstractElement {
    private List<IStationElement> evenElement;
    private List<IStationElement> oddElement;
    private Direction direction;

    public St(String code, Direction direction) {
        super(code);
        this.direction = direction;
    }


    @Override
    public IStationElement getEven() {
        return getElement(Direction.EVEN == direction, evenElement);
    }

    @Override
    public IStationElement getOdd() {
        return getElement(Direction.ODD == direction, oddElement);
    }

    private IStationElement getElement(boolean direct, List<IStationElement> elements) {
        if (direct) {
            switch (state.getState()) {
                case ST_PLUS:
                    return elements.get(0);
                case ST_MINUS:
                    return elements.get(1);
                default:
                    throw new UnknownStateCodeException("Неизвестный код" + state.getState());
            }
        } else {
            return elements.get(0);
        }
    }

    public void setEvenElement(List<IStationElement> evenElement) {
        this.evenElement = evenElement;
    }

    public void setOddElement(List<IStationElement> oddElement) {
        this.oddElement = oddElement;
    }
}
