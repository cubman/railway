package ru.dstu.railway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RailwayPolygonConfig.class)
public class TestConfig {
}
