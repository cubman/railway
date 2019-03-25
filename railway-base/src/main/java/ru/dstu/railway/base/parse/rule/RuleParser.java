package ru.dstu.railway.base.parse.rule;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.message.IMessageHolder;
import ru.dstu.railway.api.message.MessageLevel;
import ru.dstu.railway.api.parse.IParser;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.polygon.IPolygon;
import ru.dstu.railway.api.rule.IRule;
import ru.dstu.railway.api.rule.function.IFunction;
import ru.dstu.railway.api.state.IStateSender;
import ru.dstu.railway.base.parse.exception.ParseException;
import ru.dstu.railway.base.parse.rule.function.And;
import ru.dstu.railway.base.parse.rule.function.If;
import ru.dstu.railway.base.parse.rule.function.Or;
import ru.dstu.railway.base.parse.rule.function.Simple;
import ru.dstu.railway.base.parse.rule.function.description.FunctionError;
import ru.dstu.railway.base.parse.rule.function.description.FunctionResult;
import ru.dstu.railway.base.parse.rule.struct.*;
import ru.dstu.railway.model.element.AbstractElement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static ru.dstu.railway.base.parse.rule.function.description.ErrorCodes.SIMPLE_CHECK;

public class RuleParser implements IParser<List<IRule>> {

    private static final Logger LOGGER = Logger.getLogger(RuleParser.class.getName());

    private String ruleDescriptionFileName;
    private IPolygon polygon;
    private IMessageHolder messageHolder;
    private IStateSender stateSender;
    private RuleExecutor ruleExecutor;

    public RuleParser(String ruleDescriptionFileName,
                      IPolygon polygon,
                      IMessageHolder messageHolder,
                      IStateSender stateSender) {
        this.ruleDescriptionFileName = ruleDescriptionFileName;
        this.polygon = polygon;
        this.messageHolder = messageHolder;
        this.stateSender = stateSender;
        this.ruleExecutor = new RuleExecutor();
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
            List<IStationElement> elementsByType = area.getElementsByType(rule.getType());
            List<IStationElement> filterByGroup = filterByGroup(rule.getXmlGroup(), elementsByType);

            for (IStationElement element : filterByGroup) {
                IFunction preCondition = createFunction(rule.getXmlPreCondition(), element);
                IFunction postCondition = createFunction(rule.getXmlPostCondition(), element);

                Rule elementRule = new Rule(rule.getName(), preCondition,
                        postCondition, messageHolder, ruleExecutor);

                ((AbstractElement) element).addStateListener(elementRule);
                rules.add(elementRule);
            }
        }

        return rules;
    }

    private IFunction createFunction(XmlIFunction xmlFunction, IStationElement element) {
        if (xmlFunction instanceof XmlPrint) {
            return ifXmlPrint((XmlPrint) xmlFunction);
        }

        if (xmlFunction instanceof XmlCheck) {
            return ifSimple((XmlCheck) xmlFunction, element);
        }

        if (xmlFunction instanceof XmlTimer) {
            return ifTimer((XmlTimer) xmlFunction, element);
        }

        if (xmlFunction instanceof XmlSetState) {
            return ifSetState((XmlSetState) xmlFunction);
        }

        if (xmlFunction instanceof XmlIf) {
            return ifIf((XmlIf) xmlFunction, element);
        }

        if (xmlFunction instanceof XmlAnd) {
            return ifAnd((XmlAnd) xmlFunction, element);
        }

        if (xmlFunction instanceof XmlOr) {
            return ifOr((XmlOr) xmlFunction, element);
        }

        if (xmlFunction instanceof XmlCondition) {
            return ifCondition((XmlCondition) xmlFunction, element);
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

        if (xmlFunction.getXmlSetState() != null) {
            return createFunction(xmlFunction.getXmlSetState(), element);
        }

        if (xmlFunction.getXmlCheck() != null) {
            return createFunction(xmlFunction.getXmlCheck(), element);
        }

        if (xmlFunction.getXmlTimer() != null) {
            return createFunction(xmlFunction.getXmlTimer(), element);
        }

        if (xmlFunction.getXmlAnd() != null) {
            return createFunction(xmlFunction.getXmlAnd(), element);
        }

        if (xmlFunction.getXmlOr() != null) {
            return createFunction(xmlFunction.getXmlOr(), element);
        }

        throw new ParseException("Необработанный тип условия функции");
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
        switch (xmlPrint.getOut()) {
            case "console":
                return new Simple(() -> {
                    if (xmlPrint.getLevel() == null || "info".equals(xmlPrint.getLevel())) {
                        LOGGER.info(xmlPrint.getText());
                    } else if ("error".equals(xmlPrint.getLevel())) {
                        LOGGER.warning(xmlPrint.getText());
                    }
                    else {
                        throw new UnsupportedOperationException(
                                xmlPrint.getLevel() + " уровень логгирования не определен");
                    }

                    return new FunctionResult(Boolean.TRUE);
                });
            case "user":
                return new Simple(() -> {
                    if (xmlPrint.getLevel() == null || "info".equals(xmlPrint.getLevel())) {
                        messageHolder.addMessage(SIMPLE_CHECK, xmlPrint.getText(), MessageLevel.INFO);
                    } else if ("error".equals(xmlPrint.getLevel())) {
                        messageHolder.addMessage(SIMPLE_CHECK, xmlPrint.getText(), MessageLevel.ERROR);
                    }
                    else {
                        throw new UnsupportedOperationException(
                                xmlPrint.getLevel() + " уровень логгирования не определен");
                    }

                    return new FunctionResult(Boolean.TRUE);
                });
            default:
                throw new UnsupportedOperationException("Поток вывода не определен: " + xmlPrint.getOut());
        }
    }

    private IFunction ifSimple(XmlCheck xmlCheck, IStationElement stationElement) {
        if (xmlCheck.getCode() != null) {
            return new Simple(() -> new FunctionResult(stationElement.getElementCode().equals(xmlCheck.getCode())));
        } else if (xmlCheck.getState() != null) {
            return new Simple(() -> new FunctionResult(stationElement.getState().getState() == xmlCheck.getState()));
        } else {
            throw new ParseException("Сформировать условие simple не удалось по существующим правилам");
        }
    }

    private IFunction ifTimer(XmlTimer xmlFunction, IStationElement element) {
        return new Simple(() -> {
            try {
                TimeUnit.SECONDS.sleep(xmlFunction.getSleep());
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new FunctionResult(Boolean.FALSE,
                        Collections.singletonList(new FunctionError("INERRUPTED", xmlFunction.getSleep() + " не были выдержаны")));
            }
            return new FunctionResult(Boolean.TRUE);
        });
    }

    private IFunction ifSetState(XmlSetState xmlFunction) {
        return new Simple(() -> {
            IArea areaByCode = polygon.getAreaByCode(xmlFunction.getArea());
            IStationElement element = areaByCode.getElementByCode(xmlFunction.getCode());
            stateSender.sendState(areaByCode, element, xmlFunction.getState());

            return new FunctionResult(Boolean.TRUE);
        });
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
