package ru.dstu.railway.paint.figure;

public class Line implements IFigure {
    private double x1, x2, y1, y2;
    private int width;
    private int id;
    private String color;

    public Line(int id, double x1, double y1, double x2, double y2, int width) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.width = width;
        this.id = id;
        this.color = "#d3d3d3";
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }


    @Override
    public double getNextX() {
        return x2;
    }

    @Override
    public double getNextY() {
        return y2;
    }

    @Override
    public String getType() {
        return "line";
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
}
