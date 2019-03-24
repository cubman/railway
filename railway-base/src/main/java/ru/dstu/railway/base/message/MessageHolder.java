package ru.dstu.railway.base.message;

import ru.dstu.railway.api.constant.Pair;
import ru.dstu.railway.api.message.IMessageHolder;
import ru.dstu.railway.api.message.MessageLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageHolder implements IMessageHolder {
    private Map<MessageLevel, List<Pair<String, String>>> messages;

    public MessageHolder() {
        messages = new ConcurrentHashMap<>();
    }

    @Override
    public void addMessage(String code, String text, MessageLevel messageLevel) {
        messages.putIfAbsent(messageLevel, new CopyOnWriteArrayList<>());
        List<Pair<String, String>> stringList = messages.get(messageLevel);
        stringList.add(new Pair<>(code, text));
    }

    @Override
    public List<Pair<String, String>> getMessages(MessageLevel messageLevel) {
        List<Pair<String, String>> pairList = messages.get(messageLevel);
        return pairList == null ? new ArrayList<>() : pairList;
    }

    @Override
    public void clear(MessageLevel messageLevel) {
        getMessages(messageLevel).clear();
    }
}
