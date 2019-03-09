package ru.sdtu.railway.parse.logcon;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.dstu.railway.parse.IParser;
import ru.dstu.railway.parse.exception.ParseException;
import ru.dstu.railway.rule.IRule;
import ru.sdtu.railway.parse.logcon.struct.XmlRules;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RuleParser implements IParser<List<IRule>> {

    private String ruleDescriptionFileName;

    public RuleParser(String ruleDescriptionFileName) {
        this.ruleDescriptionFileName = ruleDescriptionFileName;
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


        return null;
    }
}
