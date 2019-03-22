package ru.dstu.railway.parse.polygon;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.dstu.railway.area.AbstractArea;
import ru.dstu.railway.area.IArea;
import ru.dstu.railway.area.SplitPoint;
import ru.dstu.railway.area.StageTrack;
import ru.dstu.railway.element.*;
import ru.dstu.railway.exception.DuplicationException;
import ru.dstu.railway.parse.IParser;
import ru.dstu.railway.parse.exception.ParseException;
import ru.dstu.railway.parse.polygon.struct.*;
import ru.dstu.railway.polygon.IPolygon;
import ru.dstu.railway.polygon.RailwayPolygon;

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
            linkKp(xmlArea);
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
        addElements(xmlArea, xmlArea.getKps(), Kp.class);
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

        ((AbstractElement) stationElement).addCheckRuleListener(polygon);

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

    private void linkKp(XmlArea xmlArea) {
        IArea area = areas.get(xmlArea.getCode());

        if (xmlArea.getKps() != null) {
            for (XmlKp xmlKp : xmlArea.getKps()) {
                IStationElement element = getElement(xmlArea, xmlKp.getCode());

                Kp kp = (Kp) element;
                kp.setEven(
                        getElement(
                                xmlKp.getEvenArea() != null ? xmlKp.getEvenArea() : xmlArea.getCode(),
                                xmlKp.getEvenLink()));
                kp.setOdd(
                        getElement(
                                xmlKp.getOddArea() != null ? xmlKp.getOddArea() : xmlArea.getCode(),
                                xmlKp.getOddLink()));

                kp.addArea(area);
            }
        }
    }

    private void linkSv(XmlArea xmlArea) {
        IArea area = areas.get(xmlArea.getCode());

        if (xmlArea.getSvs() != null) {
            for (XmlSv xmlSv : xmlArea.getSvs()) {
                IStationElement element = getElement(xmlArea, xmlSv.getCode());

                Sv sv = (Sv) element;
                sv.setEven(
                        getElement(
                                xmlSv.getEvenArea() != null ? xmlSv.getEvenArea() : xmlArea.getCode(),
                                xmlSv.getEvenLink()));
                sv.setOdd(
                        getElement(
                                xmlSv.getOddArea() != null ? xmlSv.getOddArea() : xmlArea.getCode(),
                                xmlSv.getOddLink()));
                sv.addArea(area);
            }
        }
    }

    private void linkPr(XmlArea xmlArea) {
        IArea area = areas.get(xmlArea.getCode());

        if (xmlArea.getPrs() != null) {
            for (XmlPr xmlPr : xmlArea.getPrs()) {
                IStationElement element = getElement(xmlArea, xmlPr.getCode());

                Pr pr = (Pr) element;

                pr.setEven(
                        getElement(
                                xmlPr.getEvenArea() != null ? xmlPr.getEvenArea() : xmlArea.getCode(),
                                xmlPr.getEvenLink()));
                pr.setOdd(
                        getElement(
                                xmlPr.getOddArea() != null ? xmlPr.getOddArea() : xmlArea.getCode(),
                                xmlPr.getOddLink()));

                pr.addArea(area);
            }
        }
    }

    private void linkMr(XmlArea xmlArea) {
        IArea area = areas.get(xmlArea.getCode());

        if (xmlArea.getMrs() != null) {
            for (XmlMr xmlMr : xmlArea.getMrs()) {
                IStationElement element = getElement(xmlArea, xmlMr.getCode());

                Mr mr = (Mr) element;

                mr.setEven(
                        getElement(
                                xmlMr.getEvenArea() != null ? xmlMr.getEvenArea() : xmlArea.getCode(),
                                xmlMr.getEvenLink()));
                mr.setOdd(
                        getElement(
                                xmlMr.getOddArea() != null ? xmlMr.getOddArea() : xmlArea.getCode(),
                                xmlMr.getOddLink()));

                mr.addArea(area);
            }
        }
    }

    private void linkUp(XmlArea xmlArea) {
        IArea area = areas.get(xmlArea.getCode());

        if (xmlArea.getUps() != null) {
            for (XmlUp xmlUp : xmlArea.getUps()) {
                IStationElement element = getElement(xmlArea, xmlUp.getCode());

                Up up = (Up) element;
                up.setEven(
                        getElement(
                                xmlUp.getEvenArea() != null ? xmlUp.getEvenArea() : xmlArea.getCode(),
                                xmlUp.getEvenLink()));
                up.setOdd(
                        getElement(
                                xmlUp.getOddArea() != null ? xmlUp.getOddArea() : xmlArea.getCode(),
                                xmlUp.getOddLink()));

                up.addArea(area);
            }
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
