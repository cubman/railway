package ru.dstu.railway.listener;

import java.util.List;

public interface IStateListener {
    /**
     * Уведомить о новом сообщении
     */
    void notify(String message);
}
