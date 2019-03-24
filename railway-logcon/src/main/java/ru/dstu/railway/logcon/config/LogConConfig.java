package ru.dstu.railway.logcon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.dstu.railway.api.message.IMessageHolder;
import ru.dstu.railway.logcon.struct.JPolygonCreator;
import ru.dstu.railway.api.paint.IPaintPolygon;
import ru.dstu.railway.paint.config.PaintConfig;
import ru.dstu.railway.base.config.RailwayPolygonConfig;
import ru.dstu.railway.api.polygon.IPolygon;

@Configuration
@Import({RailwayPolygonConfig.class, PaintConfig.class})
public class LogConConfig {
    @Bean
    public JPolygonCreator jPolygonCreator(IPolygon polygon, IPaintPolygon paintPolygon, IMessageHolder messageHolder) {
        return new JPolygonCreator(polygon, paintPolygon, messageHolder);
    }
}
