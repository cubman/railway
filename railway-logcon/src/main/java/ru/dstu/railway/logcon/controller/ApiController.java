package ru.dstu.railway.logcon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.dstu.railway.logcon.struct.JArea;
import ru.dstu.railway.logcon.struct.JPolygonCreator;

import java.util.List;

@RestController
@RequestMapping("api/")
public class ApiController {

    @Autowired
    private JPolygonCreator polygonCreator;


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @GetMapping(name = "/", produces = "application/json")
    public List<JArea> areaApi() {
        return polygonCreator.getJsonPolygon();
    }
}
