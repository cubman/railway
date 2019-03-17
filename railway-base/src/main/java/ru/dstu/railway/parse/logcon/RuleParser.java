package ru.dstu.railway.parse.logcon;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.AbstractElement;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.element.St;
import ru.dstu.railway.parse.IParser;
import ru.dstu.railway.parse.exception.ParseException;
import ru.dstu.railway.parse.logcon.function.And;
import ru.dstu.railway.parse.logcon.function.If;
import ru.dstu.railway.parse.logcon.function.Or;
import ru.dstu.railway.parse.logcon.function.Simple;
import ru.dstu.railway.parse.logcon.function.description.FunctionResult;
import ru.dstu.railway.parse.logcon.struct.*;
import ru.dstu.railway.polygon.IPolygon;
import ru.dstu.railway.rule.IRule;
import ru.dstu.railway.rule.function.IFunction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RuleParser implements IParser<List<IRule>> {

    private static final Logger LOGGER = Logger.getLogger(RuleParser.class.getName());

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

                IRule elementRule = new Rule(rule.getName(), preCondition, postCondition);

                ((AbstractElement)element).addRule(elementRule);
                rules.add(elementRule);
            }
        }

        return rules;
    }

    private IFunction createFunction(XmlIFunction xmlFunction, IStationElement element) {
        if (xmlFunction instanceof XmlPrint) {
            return ifXmlPrint((XmlPrint)xmlFunction);
        }

        if (xmlFunction instanceof XmlSimple) {
            return ifSimple((XmlSimple)xmlFunction, element);
        }

        if (xmlFunction instanceof XmlIf) {
            return ifIf((XmlIf)xmlFunction, element);
        }

        if (xmlFunction instanceof XmlAnd) {
            return ifAnd((XmlAnd)xmlFunction, element);
        }

        if (xmlFunction instanceof XmlOr) {
            return ifOr((XmlOr)xmlFunction, element);
        }

        if (xmlFunction instanceof XmlCondition) {
            return ifCondition((XmlCondition)xmlFunction, element);
        }

        throw new ParseException("Необработанный тип функции: " + xmlFunction.getClass().getName());
    }

    private IFunction ifOr(XmlOr xmlFunction, IStationElement element) {
        List<IFunction> functions = new ArrayList<>();

        if (xmlFunction.getXmlConditions() != null) {
            for (XmlCondition condition : xmlFunction.getXmlConditions()) {
                functions.add(ifCondition(condition, element));
            }
        }

        return new Or(functions);
    }

    private IFunction ifAnd(XmlAnd xmlFunction, IStationElement element) {
        List<IFunction> functions = new ArrayList<>();

        if (xmlFunction.getXmlConditions() != null) {
            for (XmlCondition condition : xmlFunction.getXmlConditions()) {
                functions.add(ifCondition(condition, element));
            }
        }

        return new And(functions);
    }

    private IFunction ifCondition(XmlCondition xmlFunction, IStationElement element) {

        if (xmlFunction.getXmlIf() != null) {
            return createFunction(xmlFunction.getXmlIf(), element);
        }

        if (xmlFunction.getXmlPrint() != null) {
            return createFunction(xmlFunction.getXmlPrint(), element);
        }

        if (xmlFunction.getXmlSimple() != null) {
            return createFunction(xmlFunction.getXmlSimple(), element);
        }

        if (xmlFunction.getXmlAnd() != null) {
            return createFunction(xmlFunction.getXmlAnd(), element);
        }

        if (xmlFunction.getXmlOr() != null) {
            return createFunction(xmlFunction.getXmlOr(), element);
        }

        throw new ParseException("Необработанный тип условия функции");
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

    private IFunction ifXmlPrint(XmlPrint xmlPrint) {
        return new Simple(() -> {
            LOGGER.info(xmlPrint.getText());
            return new FunctionResult(Boolean.TRUE);
        });
    }

    private IFunction ifSimple(XmlSimple xmlSimple, IStationElement stationElement) {
        if (xmlSimple.getCode() != null) {
            return new Simple(() -> new FunctionResult(stationElement.getElementCode().equals(xmlSimple.getCode())));
        } else
        if (xmlSimple.getState() != null) {
            return new Simple(() -> new FunctionResult(stationElement.getState().getState() == xmlSimple.getState()));
        } else {
            throw new ParseException("Сформировать условие simple не удалось по существующим правилам");
        }
    }

    private IFunction ifIf(XmlIf xmlFunction, IStationElement element) {
        IFunction ifFunction = createFunction(xmlFunction.getXmlCondition(), element);
        IFunction thenFunction = createFunction(xmlFunction.getXmlThen(), element);

        IFunction elseFunction = null;

        if (xmlFunction.getXmlElse() != null) {
            elseFunction = createFunction(xmlFunction.getXmlElse(), element);
        }

        return new If(ifFunction, thenFunction, elseFunction);
    }
}
