package ru.dstu.railway.logcon.struct;

import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.paint.IPaintPolygon;
import ru.dstu.railway.polygon.IPolygon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JPolygonCreator {

    private IPolygon polygon;
    private IPaintPolygon paintPolygon;

    public JPolygonCreator(IPolygon polygon, IPaintPolygon paintPolygon) {
        this.paintPolygon = paintPolygon;
        this.polygon = polygon;
    }

    public List<JArea> getJsonPolygon() {
        List<JArea> areas = new ArrayList<>();

        for (IArea area : polygon.getAreas()) {
            List<JElement> elements = new ArrayList<>();
            for (IStationElement element : area.getElements().values()) {
                JElement jElement = new JElement();
                jElement.setCode(element.getElementCode());
                jElement.setType(element.getClass().getSimpleName());
                jElement.setOwnAreas(getAreasForElement(element));
                jElement.setState(element.getState());
                jElement.setEven(element.getEven() == null ? null :
                        new JNextElement(element.getEven().getElementCode(),
                                element.getEven().getClass().getSimpleName(),
                                getAreasForElement(element.getEven())));
                jElement.setOdd(element.getOdd() == null ? null :
                        new JNextElement(element.getOdd().getElementCode(),
                                element.getOdd().getClass().getSimpleName(),
                                getAreasForElement(element.getOdd())));
                jElement.setFigures(paintPolygon.getElementFigures(area, element));
                elements.add(jElement);
            }
            areas.add(new JArea(area.getAreaCode(), area.getEsr(), elements));
        }

        return areas;
    }

    private List<JOwnArea> getAreasForElement(IStationElement element) {
        return element == null ? null : element.getAreas().stream()
                .map(iArea -> new JOwnArea(iArea.getAreaCode(), iArea.getEsr()))
                .collect(Collectors.toList());
    }
}
