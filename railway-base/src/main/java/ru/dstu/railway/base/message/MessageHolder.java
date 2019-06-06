package ru.dstu.railway.base.message;

import ru.dstu.railway.api.message.Message;
import ru.dstu.railway.api.message.IMessageHolder;
import ru.dstu.railway.api.message.MessageLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageHolder implements IMessageHolder {

    private Map<MessageLevel, List<Message>> messages;

    public MessageHolder() {
        messages = new ConcurrentHashMap<>();
    }

    @Override
    public void addMessage(String code, String text, MessageLevel messageLevel) {
        messages.putIfAbsent(messageLevel, new CopyOnWriteArrayList<>());
        List<Message> stringList = messages.get(messageLevel);
        stringList.add(new Message(code, text, 2));
    }

    @Override
    public List<Message> getMessages(MessageLevel messageLevel) {
        List<Message> pairList = messages.get(messageLevel);
        return pairList == null ? new ArrayList<>() : pairList;
    }

    @Override
    public void clear(MessageLevel messageLevel) {
        getMessages(messageLevel).clear();
    }
}
