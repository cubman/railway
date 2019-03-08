package ru.dstu.railway.polygon;

import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;

import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.Map;

public class RailwayPolygon implements IPolygon{
    Map<String, IArea> polygonAreas;

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
}
