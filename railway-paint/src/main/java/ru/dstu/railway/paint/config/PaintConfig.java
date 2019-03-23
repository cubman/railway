package ru.dstu.railway.paint.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dstu.railway.api.paint.IPaintPolygon;
import ru.dstu.railway.paint.parse.PaintParser;
import ru.dstu.railway.api.polygon.IPolygon;

@Configuration
public class PaintConfig {
    @Value("${file.paint}")
    private String paintFileName;

    @Bean
    IPaintPolygon paintPolygon(IPolygon polygon) {
        PaintParser paintParser = new PaintParser(paintFileName, polygon);
        return paintParser.parse();
    }
}
