package ru.sdtu.railway.parse.logcon;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.element.St;
import ru.dstu.railway.parse.IParser;
import ru.dstu.railway.parse.exception.ParseException;
import ru.dstu.railway.polygon.IPolygon;
import ru.dstu.railway.rule.IRule;
import ru.dstu.railway.rule.function.IFunction;
import ru.sdtu.railway.parse.logcon.struct.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuleParser implements IParser<List<IRule>> {

    private String ruleDescriptionFileName;
    private IPolygon polygon;

    public RuleParser(String ruleDescriptionFileName, IPolygon polygon) {
        this.ruleDescriptionFileName = ruleDescriptionFileName;
        this.polygon = polygon;
    }

    @Override
    public List<IRule> parse() {
        XmlMapper xmlMapper = new XmlMapper();
        XmlRules xmlRules;
        File ruleDescription = new File(ruleDescriptionFileName);
        try {
            xmlRules = xmlMapper.readValue(ruleDescription, XmlRules.class);
        } catch (IOException e) {
            throw new ParseException("Поток " + ruleDescription + " не распарсился", e);
        }

        return createRules(xmlRules);
    }

    private List<IRule> createRules(XmlRules xmlRules) {
        List<IRule> rules = new ArrayList<>();

        for (XmlRule rule : xmlRules.getXmlRules()) {
            IArea area = polygon.getAreaByCode(rule.getArea());
            List<IStationElement> elementsByType = area.getElementsByType(getTypeByCode(rule.getType()));
            List<IStationElement> filterByGroup = filterByGroup(rule.getXmlGroup(), elementsByType);

            for (IStationElement element : filterByGroup) {
                IFunction preCondition = createFunction(rule.getXmlPreCondition(), element);
                IFunction postCondition = createFunction(rule.getXmlPostCondition(), element);


                rules.add(new Rule(rule.getName(), preCondition, postCondition));
            }
        }

        return rules;
    }

    private IFunction createFunction(AbstractXmlFunction xmlFunction, IStationElement element) {
        return null;
    }

    private Class<? extends IStationElement> getTypeByCode(String code) {
        switch (code) {
            case "ST":
                return St.class;
            default:
                throw new IllegalArgumentException(code + " не распознан");
        }
    }

    private List<IStationElement> filterByGroup(XmlGroup group, List<IStationElement> stationElements) {
        return stationElements.stream().filter(element -> {
            for (XmlElement xmlElement : group.getElements()) {
                if (element.getElementCode().equals(xmlElement.getCode())) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
    }
}
