package ru.dstu.railway.paint.draw.drawer;

import ru.dstu.railway.paint.figure.IFigure;

import java.util.List;
import java.util.Optional;

public class BaseDraw {

    public static final String TRANSPARENT_COLOR = "#0000ffff";
    public static final String BASE_COLOR = "#000";
    public static final String NON_STATE_COLOR = "#d3d3d3";
    public static final String YELLOW_COLOR = "#e5e500";
    public static final String RED_COLOR = "#cc0000";
    public static final String GREEN_COLOR = "#58cc00";


    public static void setBaseColors(List<IFigure> figures) {
        figures.forEach(iFigure -> iFigure.setColor(BASE_COLOR));
    }

    public static void setColors(List<IFigure> figures, String color) {
        figures.forEach(iFigure -> iFigure.setColor(color));
    }

    public static void setColorsById(List<IFigure> figures, String color, int... ids) {
        for (int id : ids) {
            IFigure figure = BaseDraw.figureById(figures, id);

            figure.setColor(color);
        }
    }

    public static IFigure figureById(List<IFigure> figures, int id) {
        Optional<IFigure> first = figures.stream().filter(iFigure -> iFigure.getId() == id).findFirst();

        return first.orElse(null);
    }
}
