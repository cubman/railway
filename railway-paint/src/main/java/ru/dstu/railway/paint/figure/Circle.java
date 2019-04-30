package ru.dstu.railway.paint.figure;

import ru.dstu.railway.api.figure.IFigure;

public class Circle implements IFigure {
    private String direction;
    private double x, y, r;
    private int width;
    private int id;
    private String color;

    public Circle(int id, String direction, double x, double y, double r, int width) {
        this.direction = direction;
        this.x = getXCenter(x, r);
        this.y = getYCenter(y, r);
        this.r = r;
        this.width = width;
        this.id = id;
        this.color = "#d3d3d3";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    @Override
    public double getNextX() {
        return getXCenter(x, r);
    }

    @Override
    public double getNextY() {
        return getYCenter(y, r);
    }

    private double getXCenter(double x, double r) {
        if ("-x".equals(direction)) {
            return x - r;
        }
        if ("+x".equals(direction)) {
            return x + r;
        } else {
            return x;
        }
    }

    private double getYCenter(double y, double r) {
        if ("-y".equals(direction)) {
            return y - r;
        }
        if ("+y".equals(direction)) {
            return y + r;
        } else {
            return y;
        }
    }

    @Override
    public String getType() {
        return "circle";
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean isPlus() {
        return false;
    }
}
