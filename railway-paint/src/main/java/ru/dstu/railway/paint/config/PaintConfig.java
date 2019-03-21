package ru.dstu.railway.paint.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dstu.railway.paint.IPaintPolygon;
import ru.dstu.railway.paint.parse.PaintParser;
import ru.dstu.railway.polygon.IPolygon;

@Configuration
public class PaintConfig {
    @Bean
    IPaintPolygon paintPolygon(@Qualifier("paint") String paintFileName, IPolygon polygon) {
        PaintParser paintParser = new PaintParser(paintFileName, polygon);
        return paintParser.parse();
    }
}
