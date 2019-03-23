package ru.dstu.railway.paint.figure;

import ru.dstu.railway.api.figure.IFigure;

public class MrLabel implements IFigure {
    private double x, y;
    private String description;
    private int id;
    private int width;
    private String textColor, borderColor;

    public MrLabel(int id, double x, double y, String description, int width) {
        this.x = x;
        this.y = y;
        this.description = description;
        this.id = id;
        this.width = width;
    }

    @Override
    public double getNextX() {
        return 0;
    }

    @Override
    public double getNextY() {
        return 0;
    }

    @Override
    public String getType() {
        return "mrLabel";
    }

    @Override
    public int getWidth() {
        return width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setColor(String color) {
        throw new RuntimeException();
    }

    @Override
    public String getColor() {
        return textColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }
}
