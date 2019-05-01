package ru.dstu.railway.paint.parse;

import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.constant.Pair;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.listener.IStateListener;
import ru.dstu.railway.api.paint.IDrawElement;
import ru.dstu.railway.api.paint.IPaintPolygon;
import ru.dstu.railway.model.element.AbstractElement;
import ru.dstu.railway.model.element.Ls;
import ru.dstu.railway.paint.draw.DrawFactory;
import ru.dstu.railway.api.figure.IFigure;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaintPolygon implements IPaintPolygon, IStateListener {

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

        ((AbstractElement)element).addStateListener(this);
        Pair<Integer, List<IFigure>> listPair = listMap.putIfAbsent(element, new Pair<>(templateVersion, figures));
        if (listPair != null) {
            if (element instanceof Ls) {
                listPair.getT2().addAll(figures);
            } else {
                throw new IndexOutOfBoundsException("Множественная запись в поле: " + area + " " + element);
            }
        }
    }

    @Override
    public List<IFigure> getElementFigures(IArea area, IStationElement element) {
        return paintStructure.get(area).get(element) != null ?
                paintStructure.get(area).get(element).getT2() : new ArrayList<>();
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

        IDrawElement drawer = drawFactory.getDrawer(element, listPair.getT1());
        drawer.drawFigures(listPair.getT2());
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

    @Override
    public void notify(IArea area, IStationElement element) {
        setColors(area, element);
    }
}
