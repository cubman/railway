package ru.dstu.railway.paint.figure;

public class Label implements IFigure {

    private double x, y;
    private String description;
    private int id;

    public Label(int id, String direction, double x, double y, String description) {
        this.x = moveX(direction, x);
        this.y = moveY(direction, y);
        this.description = description;
        this.id = id;
    }

    private double moveY(String direction, double y) {
        int delta = 20;
        if ("-y".equals(direction)) {
            return y - delta;
        } else if ("+y".equals(direction)) {
            return y + delta;
        } else
            return y;
    }

    private double moveX(String direction, double x) {
        int delta = 20;
        if ("-x".equals(direction)) {
            return x - delta;
        } else if ("+x".equals(direction)) {
            return x + delta;
        } else
            return x;
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
        return "label";
    }

    @Override
    public int getWidth() {
        return 0;
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

    }

    @Override
    public String getColor() {
        return "#fff";
    }
}
