package ru.dstu.railway.api.listener;

public interface IStateListener {
    /**
     * Уведомить о новом сообщении
     */
    void notify(String message);
}
