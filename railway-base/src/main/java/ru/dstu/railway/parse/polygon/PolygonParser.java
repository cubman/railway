package ru.dstu.railway.parse.polygon;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.dstu.railway.area.IArea;
import ru.dstu.railway.area.SplitPoint;
import ru.dstu.railway.element.*;
import ru.dstu.railway.exception.DuplicationException;
import ru.dstu.railway.parse.IParser;
import ru.dstu.railway.parse.exception.ParseException;
import ru.dstu.railway.parse.polygon.struct.*;
import ru.dstu.railway.polygon.IPolygon;
import ru.dstu.railway.polygon.RailwayPolygon;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PolygonParser implements IParser<IPolygon> {

    private static final String POLYGON_DESCRIPTION = "/description/polygon.xml";

    private IPolygon polygon = new RailwayPolygon();

    Map<String, IStationElement> elements = new HashMap<>();
    Map<String, IArea> areas = new HashMap<>();

    @Override
    public IPolygon parse() {
        XmlMapper xmlMapper = new XmlMapper();
        InputStream resourceAsStream = PolygonParser.class.getResourceAsStream(POLYGON_DESCRIPTION);
        XmlPolygon xmlPolygon;
        try {
            xmlPolygon = xmlMapper.readValue(resourceAsStream, XmlPolygon.class);
        } catch (IOException e) {
            throw new ParseException("Файл " + POLYGON_DESCRIPTION + " не распарсился", e);
        }

        firstInit(xmlPolygon);
        secondInit();

        return polygon;
    }

    private void firstInit(XmlPolygon xmlPolygon) {
        for (XmlArea xmlArea : xmlPolygon.getXmlAreas()) {
            createArea(xmlArea);
            createElements(xmlArea);
        }
    }

    private void secondInit() {

    }

    private void createArea(XmlArea xmlArea) {
        IArea area;
        switch (xmlArea.getType()) {
            case "SPLITPOINT":
                area = new SplitPoint(xmlArea.getCode());
                break;
            case "STAGETRACK":

            default:
                throw new ParseException(xmlArea.getType());
        }

        if (areas.put(xmlArea.getCode(), area) != null) {
            throw new DuplicationException(xmlArea.getCode());
        }
    }

    private void createElements(XmlArea xmlArea) {
        for (XmlSt xmlSt : xmlArea.getSts()) {
            putElement(xmlArea, new St(xmlSt.getCode()));
        }

        for (XmlSv xmlSv : xmlArea.getSvs()) {
            putElement(xmlArea, new Sv(xmlSv.getCode()));
        }

        for (XmlUp xmlUp : xmlArea.getUps()) {
            putElement(xmlArea, new Up(xmlUp.getCode()));
        }

        for (XmlKp xmlKp : xmlArea.getKps()) {
            putElement(xmlArea, new Kp(xmlKp.getCode()));
        }
    }

    private void putElement(XmlArea xmlArea, IStationElement stationElement) {
        if (elements.put(xmlArea.getCode() + stationElement.getElementCode(), stationElement) != null) {
            throw new DuplicationException(stationElement.getElementCode());
        }
    }
}
