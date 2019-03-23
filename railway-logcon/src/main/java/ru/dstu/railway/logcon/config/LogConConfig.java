package ru.dstu.railway.logcon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.dstu.railway.logcon.struct.JPolygonCreator;
import ru.dstu.railway.paint.IPaintPolygon;
import ru.dstu.railway.paint.config.PaintConfig;
import ru.dstu.railway.paint.config.RailwayPolygonConfig;
import ru.dstu.railway.polygon.IPolygon;

@Configuration
@Import({RailwayPolygonConfig.class, PaintConfig.class})
public class LogConConfig {
    @Bean
    public JPolygonCreator jPolygonCreator(IPolygon polygon, IPaintPolygon paintPolygon) {
        return new JPolygonCreator(polygon, paintPolygon);
    }
}
