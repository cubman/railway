package ru.dstu.railway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dstu.railway.message.IMessageHolder;
import ru.dstu.railway.message.MessageHolder;
import ru.dstu.railway.parse.IParser;
import ru.dstu.railway.parse.polygon.PolygonParser;
import ru.dstu.railway.polygon.IPolygon;
import ru.dstu.railway.rule.IRule;
import ru.dstu.railway.parse.rule.RuleParser;

import java.util.List;

@Configuration
public class RailwayPolygonConfig {

    @Bean
    public IPolygon polygon(@Qualifier("polygon") String polygonDescriptionFileName) {
        IParser<IPolygon> polygonIParser = new PolygonParser(polygonDescriptionFileName);
        return polygonIParser.parse();
    }

    @Bean
    public List<IRule> rules(@Qualifier("rule") String ruleDescriptionFileName,
                             IPolygon polygon,
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
