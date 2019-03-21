package ru.dstu.railway.paint.parse;

import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.paint.IPaintPolygon;
import ru.dstu.railway.paint.figure.Line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaintPolygon implements IPaintPolygon {
    private Map<IArea, Map<IStationElement, List<Line>>> paintStructure;

    public PaintPolygon() {
        paintStructure = new HashMap<>();
    }

    public void addElementDraw(IArea area, IStationElement element, List<Line> lines) {
        Map<IStationElement, List<Line>> listMap = paintStructure.putIfAbsent(area, new HashMap<>());
        if (listMap == null) {
            listMap = paintStructure.get(area);
        }

        if (listMap.putIfAbsent(element, lines) != null) {
            throw new IndexOutOfBoundsException("Множественная запись в поле: " + area + " " + element);
        }
    }

    @Override
    public List<Line> getElementLines(IArea area, IStationElement element) {
        return paintStructure.get(area).get(element) != null ?
                paintStructure.get(area).get(element) : new ArrayList<>();
    }
}
