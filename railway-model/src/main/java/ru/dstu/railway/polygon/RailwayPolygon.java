package ru.dstu.railway.polygon;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.listener.ICheckedRuleListener;
import ru.dstu.railway.rule.IRule;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class RailwayPolygon implements IPolygon, ICheckedRuleListener {
    private final Map<String, IArea> polygonAreas;

    private static final int THREAD_COUNT = 10;

    private ExecutorService service;

    public RailwayPolygon() {
        this.polygonAreas = new HashMap<>();
        service = Executors.newFixedThreadPool(THREAD_COUNT);
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

    @Override
    public void notify(List<IRule> checkedRules) {
        checkedRules.forEach(rule -> service.submit(rule::execute));
    }

    @EventListener(classes = ContextClosedEvent.class)
    public void after() {
        service.shutdown();
    }
}
