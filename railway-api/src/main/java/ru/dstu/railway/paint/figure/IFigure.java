package ru.dstu.railway.paint.figure;

public interface IFigure {

    double getNextX();

    double getNextY();

    String getType();

    int getWidth();

    int getId();

    void setColor(String color);

    String getColor();
}
