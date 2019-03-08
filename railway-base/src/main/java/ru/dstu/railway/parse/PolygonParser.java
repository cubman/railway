package ru.dstu.railway.parse;

import ru.dstu.railway.polygon.IPolygon;
import ru.dstu.railway.polygon.RailwayPolygon;

import java.io.InputStream;

public class PolygonParser implements IParser<IPolygon> {

    private static final String POLYGON_DESCRIPTION = "/description/polygon.xml";

    private IPolygon polygon = new RailwayPolygon();

    @Override
    public IPolygon parse() {
        InputStream resourceAsStream = PolygonParser.class.getResourceAsStream(POLYGON_DESCRIPTION);

        firstInit();
        secondInit();

        return polygon;
    }

    private void firstInit() {

    }

    private void secondInit() {

    }
}
