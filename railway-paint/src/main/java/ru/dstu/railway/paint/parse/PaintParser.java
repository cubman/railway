package ru.dstu.railway.paint.parse;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.paint.IPaintPolygon;
import ru.dstu.railway.paint.figure.Line;
import ru.dstu.railway.paint.parse.struct.*;
import ru.dstu.railway.parse.IParser;
import ru.dstu.railway.parse.exception.ParseException;
import ru.dstu.railway.polygon.IPolygon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PaintParser implements IParser<IPaintPolygon> {

    private String paintFileName;
    private PaintPolygon paintPolygon;
    private IPolygon polygon;

    public PaintParser(String paintFileName, IPolygon polygon) {
        this.paintFileName = paintFileName;
        this.polygon = polygon;
        this.paintPolygon = new PaintPolygon();
    }

    @Override
    public IPaintPolygon parse() {
        XmlMapper xmlMapper = new XmlMapper();
        XmlPaint xmlPaint;
        File ruleDescription = new File(paintFileName);
        try {
            xmlPaint = xmlMapper.readValue(ruleDescription, XmlPaint.class);
        } catch (IOException e) {
            throw new ParseException("Поток " + ruleDescription + " не распарсился", e);
        }

        initCoordinates(xmlPaint);

        return paintPolygon;
    }

    private void initCoordinates(XmlPaint xmlPaint) {
        Random random = new Random();
        for (XmlAreaPaint areaPaint : xmlPaint.getXmlDraw().getXmlAreaPaints()) {
            IArea areaByCode = polygon.getAreaByCode(areaPaint.getCode());

            for (XmlElementPaint xmlElementPaint : areaPaint.getXmlElementPaints()) {
                IStationElement elementByCode = areaByCode.getElementByCode(xmlElementPaint.getCode());

//                List<Line> lines = new ArrayList<>();
//                for (XmlLine xmlLine : xmlElementPaint.getXmlLines()) {
//
//                    Line line = new Line(random.nextInt(1000), random.nextInt(1000),
//                            random.nextInt(1000), random.nextInt(1000));
//                    lines.add(line);
//                }
                paintPolygon.addElementDraw(areaByCode,
                        elementByCode,
                        createLines(xmlElementPaint, xmlPaint));
            }
        }
    }

    private List<Line> createLines(XmlElementPaint xmlElementPaint, XmlPaint xmlPaint) {
        List<Line> lines = new ArrayList<>();

        XmlLine element = findInitialisedElement(xmlElementPaint.getXmlLines());

        XmlTemplateElementPaint templateFigure =
                getTemplateFigure(xmlPaint, xmlElementPaint.getType(), xmlElementPaint.getVersion());

        initElementCoordinate(lines, element, templateFigure, xmlElementPaint.getXmlLines());

        return lines;
    }

    private XmlLine findInitialisedElement(List<XmlLine> xmlLines) {
        List<XmlLine> collect = xmlLines.stream()
                .filter(xmlLine -> xmlLine.getX() != null && xmlLine.getY() != null)
                .collect(Collectors.toList());

        isOnce(collect);

        return collect.get(0);
    }

    private XmlTemplateElementPaint getTemplateFigure(XmlPaint xmlPaint, String type, Integer version) {
        List<XmlTemplateElementPaint> collect = xmlPaint.getXmlBase().getXmlTemplateElementPaints().stream()
                .filter(templ -> type.equals(templ.getType()) && version.equals(templ.getVersion()))
                .collect(Collectors.toList());

        isOnce(collect);

        return collect.get(0);
    }

    private void initElementCoordinate(List<Line> resLines, XmlLine xmlLine,
                                       XmlTemplateElementPaint templateElementPaint,
                                       List<XmlLine> xmlLines) {
        List<XmlFigureObject> templateLines = templateElementPaint.getXmlTemplateLines();
        if (templateLines.size() == resLines.size()) {
            return;
        }

        XmlFigureObject objectById = getIdObjectById(templateElementPaint, xmlLine.getId());
        Line line = createLine(xmlLine.getX(), xmlLine.getY(),
                objectById.getRotate(), xmlLine.getLength());
        resLines.add(line);

        if (templateLines.size() == 1) {
            return;
        }

        List<XmlFigureObject> nextObjects = getLinkedObjectById(templateElementPaint, xmlLine.getId());

        for (XmlFigureObject nextObject : nextObjects) {
            XmlLine figureObject = getLineFigureObject(nextObject, xmlLines);

            figureObject.setX(line.getX2());
            figureObject.setY(line.getY2());

            initElementCoordinate(resLines, figureObject, templateElementPaint, xmlLines);
        }
    }

    private XmlLine getLineFigureObject(XmlFigureObject xmlFigureObject, List<XmlLine> xmlLines) {
        List<XmlLine> collect = xmlLines.stream()
                .filter(xmlLine -> xmlLine.getId().equals(xmlFigureObject.getId()))
                .collect(Collectors.toList());

        isOnce(collect);

        return collect.get(0);
    }

    private Line createLine(double x, double y, Integer rotate, int length) {
        rotate = rotate == null ? 0 : rotate;
        return new Line(x, y,
                x + Math.cos(Math.toRadians(rotate))*length,
                y + Math.sin(Math.toRadians(rotate))*length);
    }

    private double fromAngleToRadian(int angle) {
        return angle * Math.PI / 180;
    }

    private List<XmlFigureObject> getLinkedObjectById(XmlTemplateElementPaint templateElementPaint, Integer id) {
        return templateElementPaint.getXmlTemplateLines().stream()
                .filter(xmlFigureObject -> id.equals(xmlFigureObject.getLink()))
                .collect(Collectors.toList());
    }

    private XmlFigureObject getIdObjectById(XmlTemplateElementPaint templateElementPaint, Integer id) {
        List<XmlFigureObject> collect = templateElementPaint.getXmlTemplateLines().stream()
                .filter(xmlFigureObject -> id.equals(xmlFigureObject.getId()))
                .collect(Collectors.toList());

        isOnce(collect);

        return collect.get(0);
    }

    private void isOnce(Collection<?> collection) {
        if (collection.isEmpty()) {
            throw new RuntimeException("Колекция стартовой инициализации положения объекта пусто");
        }

        if (collection.size() > 1) {
            throw new RuntimeException("Колекция стартовой инициализации положения объекта не однозначно");
        }
    }


}
