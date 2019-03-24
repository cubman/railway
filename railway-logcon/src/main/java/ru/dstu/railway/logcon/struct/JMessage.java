package ru.dstu.railway.logcon.struct;

import ru.dstu.railway.api.message.MessageLevel;

public class JMessage {
    private MessageLevel messageLevel;
    private String code;
    private String description;

    public MessageLevel getMessageLevel() {
        return messageLevel;
    }

    public void setMessageLevel(MessageLevel messageLevel) {
        this.messageLevel = messageLevel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
