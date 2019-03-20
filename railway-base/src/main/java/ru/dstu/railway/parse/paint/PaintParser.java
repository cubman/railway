package ru.dstu.railway.parse.paint;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.paint.IPaintPolygon;
import ru.dstu.railway.paint.Line;
import ru.dstu.railway.parse.IParser;
import ru.dstu.railway.parse.exception.ParseException;
import ru.dstu.railway.parse.paint.struct.*;
import ru.dstu.railway.polygon.IPolygon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

                List<Line> lines = new ArrayList<>();
                for (XmlLine xmlLine : xmlElementPaint.getXmlLines()) {

                    Line line = new Line(random.nextInt(1000), random.nextInt(1000),
                            random.nextInt(1000), random.nextInt(1000));
                    lines.add(line);
                }
                paintPolygon.addElementDraw(areaByCode, elementByCode, lines);
            }
        }
    }
}
