package ru.dstu.railway.api.message;

import ru.dstu.railway.api.constant.Pair;

import java.util.List;

public interface IMessageHolder {

    void addMessage(String code, String text, MessageLevel messageLevel);

    List<Message> getMessages(MessageLevel messageLevel);

    void clear(MessageLevel messageLevel);
}
