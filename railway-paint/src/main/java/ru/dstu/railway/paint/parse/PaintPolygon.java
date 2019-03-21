package ru.dstu.railway.paint.parse;

import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.element.St;
import ru.dstu.railway.paint.IPaintPolygon;
import ru.dstu.railway.paint.draw.DrawSt;
import ru.dstu.railway.paint.figure.IFigure;
import ru.dstu.railway.paint.figure.Line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaintPolygon implements IPaintPolygon {
    public static final String TRANSPARENT_COLOR = "#0000ffff";
    public static final String BASE_COLOR = "#000";
    public static final String NON_STATE_COLOR = "#d3d3d3";

    private Map<IArea, Map<IStationElement, List<IFigure>>> paintStructure;

    public PaintPolygon() {
        paintStructure = new HashMap<>();
    }

    public void addElementDraw(IArea area, IStationElement element, List<IFigure> figures) {
        Map<IStationElement, List<IFigure>> listMap = paintStructure.putIfAbsent(area, new HashMap<>());
        if (listMap == null) {
            listMap = paintStructure.get(area);
        }

        if (listMap.putIfAbsent(element, figures) != null) {
            throw new IndexOutOfBoundsException("Множественная запись в поле: " + area + " " + element);
        }
    }

    @Override
    public List<IFigure> getElementFigures(IArea area, IStationElement element) {
        return paintStructure.get(area).get(element) != null ?
                paintStructure.get(area).get(element) : new ArrayList<>();
    }

    @Override
    public void setColors(IArea area, IStationElement element) {
        if (area == null || element == null) {
            return;
        }

        List<IFigure> figures = getElementFigures(area, element);

        if (element instanceof St) {
            DrawSt.updateColors(element, figures);
        }
    }


}
