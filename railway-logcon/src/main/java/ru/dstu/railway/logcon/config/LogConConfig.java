package ru.dstu.railway.logcon.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.dstu.railway.config.PaintConfig;
import ru.dstu.railway.config.RailwayPolygonConfig;
import ru.dstu.railway.logcon.controller.WebController;
import ru.dstu.railway.logcon.struct.JPolygonCreator;
import ru.dstu.railway.paint.IPaintPolygon;
import ru.dstu.railway.parse.polygon.PolygonParser;
import ru.dstu.railway.parse.polygon.struct.XmlPolygon;
import ru.dstu.railway.polygon.IPolygon;

import java.net.URL;

@Configuration
@Import({RailwayPolygonConfig.class, PaintConfig.class})
public class LogConConfig {
    @Bean
    public JPolygonCreator jPolygonCreator(IPolygon polygon, IPaintPolygon paintPolygon) {
        return new JPolygonCreator(polygon, paintPolygon);
    }

    @Bean
    @Qualifier("polygon")
    public String polygonDescriptionFile() {
        URL resource = LogConConfig.class.getResource("/description/polygon.xml");
        return resource.getPath();
    }

    @Bean
    @Qualifier("rule")
    public String ruleDescriptionFile() {
        URL resource = LogConConfig.class.getResource("/description/rules.xml");
        return resource.getPath();
    }

    @Bean
    @Qualifier("paint")
    public String paintDescriptionFile() {
        URL resource = LogConConfig.class.getResource("/description/paint.xml");
        return resource.getPath();
    }
}
