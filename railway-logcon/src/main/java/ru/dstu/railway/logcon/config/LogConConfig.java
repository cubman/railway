package ru.dstu.railway.logcon.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.dstu.railway.config.RailwayPolygonConfig;
import ru.dstu.railway.logcon.controller.WebController;

import java.net.URL;

@Configuration
@Import(RailwayPolygonConfig.class)
public class LogConConfig {
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
}
