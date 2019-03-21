package ru.dstu.railway.paint.figure;

public class Circle implements IFigure {
    private String direction;
    private double x, y, r;

    public Circle(String direction, double x, double y, double r) {
        this.direction = direction;
        this.x = getXCenter(x, r);
        this.y = getYCenter(y, r);
        this.r = r;
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
        } if ("+x".equals(direction)) {
            return x + r;
        }else {
            return x;
        }
    }

    private double getYCenter(double y, double r) {
        if ("-y".equals(direction)) {
            return y - r;
        } if ("+y".equals(direction)) {
            return y + r;
        }else {
            return y;
        }
    }

    @Override
    public String getType() {
        return "circle";
    }
}
