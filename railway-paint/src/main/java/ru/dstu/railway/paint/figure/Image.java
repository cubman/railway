package ru.dstu.railway.paint.figure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.dstu.railway.api.figure.IFigure;

public class Image implements IFigure {
    private String path;
    private double x, y;
    private int scale;
    private int id;

    public Image(int id, String path, double x, double y, int scale) {
        this.path = path;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getPath() {
        return path;
    }

    @Override
    @JsonIgnore
    public double getNextX() {
        throw new UnsupportedOperationException("Получение следующей координаты нереализовано");
    }

    @Override
    @JsonIgnore
    public double getNextY() {
        throw new UnsupportedOperationException("Получение следующей координаты нереализовано");
    }

    @Override
    public String getType() {
        return "image";
    }

    @Override
    public int getWidth() {
        return scale;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setColor(String color) {
        throw new UnsupportedOperationException("Для картинки нельзя выставить цвет");
    }

    @Override
    @JsonIgnore
    public String getColor() {
        throw new UnsupportedOperationException("Для картинки нельзя получить цвет");
    }

    @Override
    public boolean isPlus() {
        return false;
    }
}
