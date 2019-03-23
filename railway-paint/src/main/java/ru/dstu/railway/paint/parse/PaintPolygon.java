package ru.dstu.railway.paint.parse;

import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.paint.IDrawElement;
import ru.dstu.railway.paint.IPaintPolygon;
import ru.dstu.railway.paint.draw.DrawFactory;
import ru.dstu.railway.paint.figure.IFigure;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaintPolygon implements IPaintPolygon {

    private class Pair<T1, T2> {
        T1 t1;
        T2 t2;

        Pair(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }
    }

    private Map<IArea, Map<IStationElement, Pair<Integer, List<IFigure>>>> paintStructure;

    private DrawFactory drawFactory = new DrawFactory();

    public PaintPolygon() {
        paintStructure = new HashMap<>();
    }

    public void addElementDraw(IArea area, IStationElement element, int templateVersion, List<IFigure> figures) {
        Map<IStationElement, Pair<Integer, List<IFigure>>> listMap = paintStructure.putIfAbsent(area, new HashMap<>());
        if (listMap == null) {
            listMap = paintStructure.get(area);
        }

        if (listMap.putIfAbsent(element, new Pair<>(templateVersion, figures)) != null) {
            throw new IndexOutOfBoundsException("Множественная запись в поле: " + area + " " + element);
        }
    }

    @Override
    public List<IFigure> getElementFigures(IArea area, IStationElement element) {
        return paintStructure.get(area).get(element) != null ?
                paintStructure.get(area).get(element).t2 : new ArrayList<>();
    }

    private Pair<Integer, List<IFigure>> getElement(IArea area, IStationElement element) {
        return paintStructure.get(area).get(element);
    }

    @Override
    public void setColors(IArea area, IStationElement element) {
        if (area == null || element == null) {
            return;
        }

        Pair<Integer, List<IFigure>> listPair = getElement(area, element);

        IDrawElement drawer = drawFactory.getDrawer(element, listPair.t1);
        drawer.drawColors(listPair.t2);
//        List<IFigure> figures = getElementFigures(area, element);
//
//        if (element instanceof St) {
//            DrawSt.updateColors(element, figures);
//        } else if (element instanceof Up) {
//            DrawUp.updateColors(element, figures);
//        } else if (element instanceof Pt) {
//            DrawPt.updateColors(element, figures);
//        } else if (element instanceof Sv) {
//            DrawSv.updateColors(element, figures);
//        }
    }

    @Override
    public void refresh() {
        paintStructure.forEach((area, iStationElementListMap) ->
                iStationElementListMap.forEach((element, figures) ->
                        setColors(area, element)
                ));
    }

    @PostConstruct
    public void postConstruct() {
        refresh();
    }

}
