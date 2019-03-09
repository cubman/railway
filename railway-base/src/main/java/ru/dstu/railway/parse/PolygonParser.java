package ru.dstu.railway.parse;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.dstu.railway.parse.exception.ParseException;
import ru.dstu.railway.polygon.IPolygon;
import ru.dstu.railway.polygon.RailwayPolygon;

import java.io.IOException;
import java.io.InputStream;

public class PolygonParser implements IParser<IPolygon> {

    private static final String POLYGON_DESCRIPTION = "/description/polygon.xml";

    private IPolygon polygon = new RailwayPolygon();

    @Override
    public IPolygon parse() {
        XmlMapper xmlMapper = new XmlMapper();
        InputStream resourceAsStream = PolygonParser.class.getResourceAsStream(POLYGON_DESCRIPTION);

        try {
            Object readValue = xmlMapper.readValue(resourceAsStream, Object.class);
        } catch (IOException e) {
             throw new ParseException("Файл " + POLYGON_DESCRIPTION + " не распарсился", e);
        }

        firstInit();
        secondInit();

        return polygon;
    }

    private void firstInit() {

    }

    private void secondInit() {

    }
}
