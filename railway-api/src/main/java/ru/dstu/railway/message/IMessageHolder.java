package ru.dstu.railway.message;

import java.util.List;

public interface IMessageHolder {

    void addMessage(String text);

    List<String> getMessages();
}
