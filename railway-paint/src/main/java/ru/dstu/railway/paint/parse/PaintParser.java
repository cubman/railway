package ru.dstu.railway.paint.parse;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.paint.IPaintPolygon;
import ru.dstu.railway.api.figure.*;
import ru.dstu.railway.paint.figure.Circle;
import ru.dstu.railway.paint.figure.Label;
import ru.dstu.railway.paint.figure.Line;
import ru.dstu.railway.paint.figure.MrLabel;
import ru.dstu.railway.paint.parse.struct.*;
import ru.dstu.railway.api.base.parse.IParser;
import ru.dstu.railway.base.parse.exception.ParseException;
import ru.dstu.railway.api.polygon.IPolygon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        for (XmlAreaPaint areaPaint : xmlPaint.getXmlDraw().getXmlAreaPaints()) {
            IArea areaByCode = polygon.getAreaByCode(areaPaint.getCode());

            for (XmlElementPaint xmlElementPaint : areaPaint.getXmlElementPaints()) {
                IStationElement elementByCode = areaByCode.getElementByCode(xmlElementPaint.getCode());

                XmlTemplateElementPaint templateFigure =
                        getTemplateFigure(xmlPaint, xmlElementPaint.getType(), xmlElementPaint.getVersion());

                paintPolygon.addElementDraw(areaByCode,
                        elementByCode, templateFigure.getVersion(),
                        createFigures(xmlElementPaint, templateFigure));
            }
        }
    }

    private List<IFigure> createFigures(XmlElementPaint xmlElementPaint,
                                        XmlTemplateElementPaint templateFigure) {
        List<IFigure> lines = new ArrayList<>();

        XmlFigure element = findInitialisedElement(xmlElementPaint.getXmlFigures());

        initElementCoordinate(lines, element, templateFigure, xmlElementPaint.getXmlFigures());

        return lines;
    }

    private XmlFigure findInitialisedElement(List<XmlFigure> xmlFigures) {
        List<XmlFigure> collect = xmlFigures.stream()
                .filter(xmlFigure -> xmlFigure.getX() != null && xmlFigure.getY() != null)
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

    private void initElementCoordinate(List<IFigure> resLines, XmlFigure xmlFigure,
                                       XmlTemplateElementPaint templateElementPaint,
                                       List<XmlFigure> xmlLines) {
        List<XmlFigureObject> templateLines = templateElementPaint.getXmlTemplateLines();
        if (templateLines.size() == resLines.size()) {
            return;
        }

        XmlFigureObject objectById = getIdObjectById(templateElementPaint, xmlFigure.getId());
        IFigure figure;

        switch (objectById.getType()) {
            case "line":
                figure = createLine(xmlFigure.getId(),
                        xmlFigure.getX(), xmlFigure.getY(),
                        objectById.getRotate(), xmlFigure.getLength(),
                        objectById.getWidth());
                break;
            case "circle":
                figure = createCircle(xmlFigure.getId(),
                        objectById.getPos(),
                        xmlFigure.getX(), xmlFigure.getY(),
                        xmlFigure.getLength(),
                        objectById.getWidth());
                break;
            case "label":
                figure = createLabel(xmlFigure.getId(),
                        objectById.getPos(),
                        xmlFigure.getX(), xmlFigure.getY(),
                        xmlFigure.getDescription(),
                        objectById.getWidth());
                break;
            case "mrLabel":
                figure = createMrLabel(xmlFigure.getId(),
                        xmlFigure.getX(), xmlFigure.getY(),
                        xmlFigure.getDescription(),
                        objectById.getWidth());
                break;
            default:
                throw new RuntimeException();
        }

        resLines.add(figure);

        if (templateLines.size() == 1) {
            return;
        }

        List<XmlFigureObject> nextObjects = getLinkedObjectById(templateElementPaint, xmlFigure.getId());

        for (XmlFigureObject nextObject : nextObjects) {
            XmlFigure figureObject = getLineFigureObject(nextObject, xmlLines);

            figureObject.setX(figure.getNextX());
            figureObject.setY(figure.getNextY());

            initElementCoordinate(resLines, figureObject, templateElementPaint, xmlLines);
        }
    }

    private XmlFigure getLineFigureObject(XmlFigureObject xmlFigureObject, List<XmlFigure> xmlLines) {
        List<XmlFigure> collect = xmlLines.stream()
                .filter(xmlLine -> xmlLine.getId().equals(xmlFigureObject.getId()))
                .collect(Collectors.toList());

        isOnce(collect);

        return collect.get(0);
    }

    private Line createLine(int id, double x, double y, Integer rotate, int length, int width) {
        rotate = rotate == null ? 0 : rotate;
        return new Line(id, x, y,
                x + Math.cos(Math.toRadians(rotate)) * length,
                y + Math.sin(Math.toRadians(rotate)) * length,
                width);
    }

    private Circle createCircle(int id, String direction, double x, double y, int r, int width) {
        return new Circle(id, direction, x, y, r, width);
    }

    private Label createLabel(int id, String direction, double x, double y, String text, int width) {
        return new Label(id, direction, x, y, text, width);
    }

    private MrLabel createMrLabel(int id, double x, double y, String text, int width) {
        return new MrLabel(id, x, y, text, width);
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
