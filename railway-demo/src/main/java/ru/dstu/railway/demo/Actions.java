package ru.dstu.railway.demo;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.message.IMessageHolder;
import ru.dstu.railway.api.message.MessageLevel;

import static ru.dstu.railway.base.parse.rule.function.description.ErrorCodes.SOUND_CHECK;

public class Actions {

    @Autowired
    IMessageHolder messageHolder;

    public boolean notifyStation(IStationElement element) {
        messageHolder.addMessage(SOUND_CHECK, "station.ac3", MessageLevel.VOICE);

        return true;
    }
}
