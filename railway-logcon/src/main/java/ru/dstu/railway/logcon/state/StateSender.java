package ru.dstu.railway.logcon.state;

import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.state.IStateSender;

import java.util.logging.Logger;

public class StateSender implements IStateSender {
    private static final Logger LOGGER = Logger.getLogger(StateSender.class.getName());

    @Override
    public void sendState(IArea area, IStationElement stationElement, int state) {
        LOGGER.info("Выставление состояния: " +
                area.toString() + " | " + stationElement+ " | " + state);

//        stationElement.setState(state);
    }
}
