package ru.dstu.railway.api.figure;

public interface IFigure {

    double getNextX();

    double getNextY();

    String getType();

    int getWidth();

    int getId();

    void setColor(String color);

    String getColor();

    boolean isPlus();
}
