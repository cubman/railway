package ru.dstu.railway.message;

import java.util.List;

public interface IMessageHolder {

    void addMessage(String text, MessageLevel messageLevel);

    List<String> getMessages(MessageLevel messageLevel);
}
