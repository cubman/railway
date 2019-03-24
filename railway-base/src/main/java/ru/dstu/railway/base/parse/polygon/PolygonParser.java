package ru.dstu.railway.base.parse.polygon;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.parse.IParser;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.polygon.IPolygon;
import ru.dstu.railway.base.parse.exception.ParseException;
import ru.dstu.railway.base.parse.polygon.struct.*;
import ru.dstu.railway.model.area.AbstractArea;
import ru.dstu.railway.model.area.SplitPoint;
import ru.dstu.railway.model.area.StageTrack;
import ru.dstu.railway.model.element.*;
import ru.dstu.railway.model.exception.DuplicationException;
import ru.dstu.railway.model.polygon.RailwayPolygon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolygonParser implements IParser<IPolygon> {

    private RailwayPolygon polygon = new RailwayPolygon();
    private XmlPolygon xmlPolygon;

    private final Map<String, IStationElement> elements = new HashMap<>();
    private final Map<String, IArea> areas = new HashMap<>();

    public PolygonParser(String polygonDescriptionFileName) {
        XmlMapper xmlMapper = new XmlMapper();

        File polygonDescription = new File(polygonDescriptionFileName);
        try {
            xmlPolygon = xmlMapper.readValue(polygonDescription, XmlPolygon.class);
        } catch (IOException e) {
            throw new ParseException("Поток " + polygonDescription + " не распарсился", e);
        }
    }

    @Override
    public IPolygon parse() {
        firstInit(xmlPolygon);
        secondInit(xmlPolygon);

        return polygon;
    }

    private void firstInit(XmlPolygon xmlPolygon) {
        for (XmlArea xmlArea : xmlPolygon.getXmlAreas()) {
            createArea(xmlArea);
            createElements(xmlArea);

            putElement(xmlArea, new Ls(""));
        }
    }

    private void secondInit(XmlPolygon xmlPolygon) {
        for (XmlArea xmlArea : xmlPolygon.getXmlAreas()) {
            linkSt(xmlArea);
            linkPt(xmlArea);
            linkUp(xmlArea);
            linkSv(xmlArea);
            linkPr(xmlArea);
            linkMr(xmlArea);

            addParty(xmlArea);

            IArea area = areas.get(xmlArea.getCode());
            if (area.getElementByCode("") == null) {
                area.addElement(new Ls(""));
            }

            polygon.addArea(areas.get(xmlArea.getCode()));
        }
    }

    private void createArea(XmlArea xmlArea) {
        IArea area;
        switch (xmlArea.getType()) {
            case "SPLITPOINT":
                area = new SplitPoint(xmlArea.getCode(), xmlArea.getEsr());
                break;
            case "STAGETRACK":
                area = new StageTrack(xmlArea.getCode(), xmlArea.getEsr());
                break;
            default:
                throw new ParseException(xmlArea.getType());
        }

        if (areas.put(xmlArea.getCode(), area) != null) {
            throw new DuplicationException(xmlArea.getCode());
        }
    }

    private void createElements(XmlArea xmlArea) {
        addElements(xmlArea, xmlArea.getSts(), St.class);
        addElements(xmlArea, xmlArea.getSvs(), Sv.class);
        addElements(xmlArea, xmlArea.getUps(), Up.class);
        addElements(xmlArea, xmlArea.getPts(), Pt.class);
        addElements(xmlArea, xmlArea.getPrs(), Pr.class);
        addElements(xmlArea, xmlArea.getMrs(), Mr.class);
    }

    private void addElements(XmlArea xmlArea, List<? extends AbstractXmlElement> elements,
                             Class<? extends IStationElement> elementClass) {
        if (elements != null) {
            IArea area = areas.get(xmlArea.getCode());

            for (AbstractXmlElement xmlElement : elements) {
                IStationElement element;
                try {
                    element = elementClass.newInstance();
                    ((AbstractElement) element).setElementCode(xmlElement.getCode());
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                area.addElement(putElement(xmlArea, element));
            }
        }
    }

    private IStationElement putElement(XmlArea xmlArea, IStationElement stationElement) {
        if (elements.put(xmlArea.getCode() + stationElement.getElementCode(), stationElement) != null) {
            throw new DuplicationException(stationElement.getElementCode());
        }

        return stationElement;
    }

    private IStationElement getElement(XmlArea xmlArea, String elementCode) {
        return getElement(xmlArea.getCode(), elementCode);
    }

    private IStationElement getElement(String areaCode, String elementCode) {
        IStationElement element = elements.get(areaCode + elementCode);

        if (element == null) {
            throw new IndexOutOfBoundsException("Объект не найден: " + areaCode + " = " + elementCode);
        }

        return element;
    }

    private void linkSt(XmlArea xmlArea) {
        IArea area = areas.get(xmlArea.getCode());

        if (xmlArea.getSts() != null) {
            for (XmlSt xmlSt : xmlArea.getSts()) {
                IStationElement element = getElement(xmlArea, xmlSt.getCode());

                List<IStationElement> even = new ArrayList<>();
                even.add(getElement(xmlArea, xmlSt.getEvenLinkP()));
                even.add(getElement(xmlArea, xmlSt.getEvenLinkM()));

                List<IStationElement> odd = new ArrayList<>();
                odd.add(getElement(xmlArea, xmlSt.getOddLinkP()));
                odd.add(getElement(xmlArea, xmlSt.getOddLinkM()));

                St st = (St) element;
                st.setOddElement(odd);
                st.setEvenElement(even);

                st.addArea(area);
            }
        }
    }

    private void linkPt(XmlArea xmlArea) {
        linkSingleNeighbour(xmlArea, xmlArea.getPts());
    }

    private void linkSv(XmlArea xmlArea) {
        linkSingleNeighbour(xmlArea, xmlArea.getSvs());
    }

    private void linkPr(XmlArea xmlArea) {
        linkSingleNeighbour(xmlArea, xmlArea.getPrs());
    }

    private void linkMr(XmlArea xmlArea) {
        linkSingleNeighbour(xmlArea, xmlArea.getMrs());
    }

    private void linkUp(XmlArea xmlArea) {
        linkSingleNeighbour(xmlArea, xmlArea.getUps());
    }

    private void linkSingleNeighbour(XmlArea xmlArea, List<? extends AbstractXmlElement> list) {
        if (list == null) {
            return;
        }

        IArea area = areas.get(xmlArea.getCode());

        for (AbstractXmlElement xmlElement : list) {
            IStationElement element = getElement(xmlArea, xmlElement.getCode());

            SingleLinkNeigbour linkNeigbour = (SingleLinkNeigbour) element;
            linkNeigbour.setEven(
                    getElement(
                            xmlElement.getEvenArea() != null ? xmlElement.getEvenArea() : xmlArea.getCode(),
                            xmlElement.getEvenLink()));
            linkNeigbour.setOdd(
                    getElement(
                            xmlElement.getOddArea() != null ? xmlElement.getOddArea() : xmlArea.getCode(),
                            xmlElement.getOddLink()));

            linkNeigbour.addArea(area);
        }
    }

    private void addParty(XmlArea xmlArea) {
        if (xmlArea.getParties() != null) {
            for (XmlParty xmlParty : xmlArea.getParties()) {
                IStationElement elementFrom = getElement(xmlArea, xmlParty.getFirstElement());
                IStationElement elementTo = getElement(xmlArea, xmlParty.getSecondElement());

                IArea area = areas.get(xmlArea.getCode());
                ((AbstractArea) area).addChangeParty(elementFrom, elementTo);
                ((AbstractArea) area).addChangeParty(elementTo, elementFrom);
            }
        }
    }
}
