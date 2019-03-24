package ru.dstu.railway.model.polygon;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.polygon.IPolygon;

import java.util.*;
import java.util.concurrent.ExecutorService;


public class RailwayPolygon implements IPolygon {
    private final Map<String, IArea> polygonAreas;



    private ExecutorService service;

    public RailwayPolygon() {
        this.polygonAreas = new HashMap<>();

    }

    @Override
    public void addArea(IArea area) {
        if (polygonAreas.putIfAbsent(area.getAreaCode(), area) != null) {
            throw new DuplicateFormatFlagsException(area.getAreaCode() + " уже существует");
        }
    }

    @Override
    public IArea getAreaByCode(String code) {
        return polygonAreas.get(code);
    }

    @Override
    public IStationElement getElementByAreaAndCode(String areaCode, String elementCode) {
        IArea areaByCode = getAreaByCode(areaCode);

        return areaByCode != null ? areaByCode.getElementByCode(elementCode) : null;
    }

    @Override
    public List<IArea> getAreas() {
        return new ArrayList<>(polygonAreas.values());
    }

    @EventListener(classes = ContextClosedEvent.class)
    public void after() {
        service.shutdown();
    }
}
