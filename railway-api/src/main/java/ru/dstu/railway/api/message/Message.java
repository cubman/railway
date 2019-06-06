package ru.dstu.railway.api.message;

public class Message {
    private String code;
    private String text;
    private int life;

    public Message(String code, String text, int life) {
        this.code = code;
        this.text = text;
        this.life = life;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public int getLife() {
        return life;
    }

    public void downLife() {
        --life;
    }
}