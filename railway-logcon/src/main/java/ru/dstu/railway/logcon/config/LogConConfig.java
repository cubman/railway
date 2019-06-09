package ru.dstu.railway.logcon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.dstu.railway.api.message.IMessageHolder;
import ru.dstu.railway.api.paint.IPaintPolygon;
import ru.dstu.railway.api.polygon.IPolygon;
import ru.dstu.railway.api.state.IStateSender;
import ru.dstu.railway.base.config.RailwayPolygonConfig;
import ru.dstu.railway.demo.config.DemoConfig;
import ru.dstu.railway.logcon.state.StateSender;
import ru.dstu.railway.logcon.struct.JPolygonCreator;
import ru.dstu.railway.paint.config.PaintConfig;
import ru.dstu.railway.storage.config.StorageConfig;

import javax.sql.DataSource;

@Configuration
@Import({RailwayPolygonConfig.class, PaintConfig.class, DemoConfig.class})
public class LogConConfig {
    @Bean
    public JPolygonCreator jPolygonCreator(IPolygon polygon, IPaintPolygon paintPolygon, IMessageHolder messageHolder) {
        return new JPolygonCreator(polygon, paintPolygon, messageHolder);
    }

    @Bean
    public IStateSender stateSender() {
        return new StateSender();
    }
}
