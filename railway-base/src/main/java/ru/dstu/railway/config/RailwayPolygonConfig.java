package ru.dstu.railway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dstu.railway.message.IMessageHolder;
import ru.dstu.railway.message.MessageHolder;
import ru.dstu.railway.paint.IPaintPolygon;
import ru.dstu.railway.parse.IParser;
import ru.dstu.railway.parse.paint.PaintParser;
import ru.dstu.railway.parse.polygon.PolygonParser;
import ru.dstu.railway.parse.rule.RuleParser;
import ru.dstu.railway.polygon.IPolygon;
import ru.dstu.railway.rule.IRule;

import java.util.List;

@Configuration
public class RailwayPolygonConfig {

    @Bean
    public IPolygon polygon(@Qualifier("polygon") String polygonDescriptionFileName) {
        PolygonParser polygonParser = new PolygonParser(polygonDescriptionFileName);
        return polygonParser.parse();
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
    IPaintPolygon paintPolygon(@Qualifier("paint") String paintFileName, IPolygon polygon) {
        PaintParser paintParser = new PaintParser(paintFileName, polygon);
        return paintParser.parse();
    }

    @Bean
    public IMessageHolder messageHolder() {
        return new MessageHolder();
    }
}
