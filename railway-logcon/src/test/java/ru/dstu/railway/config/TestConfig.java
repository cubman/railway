package ru.dstu.railway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

@Configuration
@Import(RailwayPolygonConfig.class)
public class TestConfig {

    @Bean
    @Qualifier("polygon")
    public String polygonDescriptionFile() {
        URL resource = TestConfig.class.getResource("/description/polygon.xml");
        return resource.getPath();
    }
}