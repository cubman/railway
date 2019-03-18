package ru.dstu.railway.listener;

import java.util.List;

public interface IMessageListener {
    /**
     * Уведомить о новом сообщении
     */
    void notify(String message);
}
