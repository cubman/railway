package ru.dstu.railway.message;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageHolder implements IMessageHolder {
    private List<String> messages;

    public MessageHolder() {
        messages = new CopyOnWriteArrayList<>();
    }

    @Override
    public void addMessage(String text) {
        messages.add(text);
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
}
