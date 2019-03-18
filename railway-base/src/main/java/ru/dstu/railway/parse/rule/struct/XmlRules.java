package ru.dstu.railway.parse.rule.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "rules")
public class XmlRules {

    @JacksonXmlProperty(localName = "rule")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlRule> xmlRules;

    public List<XmlRule> getXmlRules() {
        return xmlRules;
    }

    public void setXmlRules(List<XmlRule> xmlRules) {
        this.xmlRules = xmlRules;
    }
}
