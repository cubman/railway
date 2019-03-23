package ru.dstu.railway.base.message;

import ru.dstu.railway.api.base.message.IMessageHolder;
import ru.dstu.railway.api.base.message.MessageLevel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageHolder implements IMessageHolder {
    private Map<MessageLevel, List<String>> messages;

    public MessageHolder() {
        messages = new ConcurrentHashMap<>();
    }

    @Override
    public void addMessage(String text, MessageLevel messageLevel) {
        messages.putIfAbsent(messageLevel, new CopyOnWriteArrayList<>());
        List<String> stringList = messages.get(messageLevel);
        stringList.add(text);
    }

    @Override
    public List<String> getMessages(MessageLevel messageLevel) {
        return messages.get(messageLevel);
    }
}
