package ru.dstu.railway.paint.draw;

import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.paint.figure.IFigure;

import java.util.List;
import java.util.Optional;

import static ru.dstu.railway.paint.parse.PaintPolygon.BASE_COLOR;
import static ru.dstu.railway.paint.parse.PaintPolygon.TRANSPARENT_COLOR;

public class BaseDraw {

    public static void setBaseColors(List<IFigure> figures) {
        figures.forEach(iFigure -> iFigure.setColor(BASE_COLOR));
    }

    public static void setColors(List<IFigure> figures, String color) {
        figures.forEach(iFigure -> iFigure.setColor(color));
    }

    public static void setColorsById(List<IFigure> figures, int id, String color) {
        IFigure figure = BaseDraw.figureById(figures, id);

        figure.setColor(color);
    }

    public static IFigure figureById(List<IFigure> figures, int id) {
        Optional<IFigure> first = figures.stream().filter(iFigure -> iFigure.getId() == id).findFirst();

        return first.orElse(null);
    }
}
