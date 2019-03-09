package ru.dstu.railway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dstu.railway.parse.IParser;
import ru.dstu.railway.parse.polygon.PolygonParser;
import ru.dstu.railway.polygon.IPolygon;

@Configuration
public class RailwayPolygonConfig {

    @Bean
    IPolygon polygon(@Qualifier("polygon") String polygonDescriptionFileName) {
        IParser<IPolygon> polygonIParser = new PolygonParser(polygonDescriptionFileName);
        return polygonIParser.parse();
    }

}
