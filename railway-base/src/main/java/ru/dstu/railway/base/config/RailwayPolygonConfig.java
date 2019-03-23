package ru.dstu.railway.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dstu.railway.api.base.message.IMessageHolder;
import ru.dstu.railway.base.message.MessageHolder;
import ru.dstu.railway.api.base.parse.IParser;
import ru.dstu.railway.base.parse.polygon.PolygonParser;
import ru.dstu.railway.base.parse.rule.RuleParser;
import ru.dstu.railway.api.polygon.IPolygon;
import ru.dstu.railway.api.rule.IRule;

import java.util.List;

@Configuration
public class RailwayPolygonConfig {

    @Value("${file.polygon}")
    private String polygonDescriptionFileName;
    @Value("${file.rule}")
    private String ruleDescriptionFileName;

    @Bean
    public IPolygon polygon() {
        PolygonParser polygonParser = new PolygonParser(polygonDescriptionFileName);
        return polygonParser.parse();
    }

    @Bean
    public List<IRule> rules(IPolygon polygon,
                             IMessageHolder messageHolder) {
        IParser<List<IRule>> ruleParser =
                new RuleParser(ruleDescriptionFileName, polygon, messageHolder);
        return ruleParser.parse();
    }

    @Bean
    public IMessageHolder messageHolder() {
        return new MessageHolder();
    }
}
