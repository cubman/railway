package ru.dstu.railway.base.parse.rule.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class XmlNot implements XmlIFunction {
    @JacksonXmlProperty(localName = "condition")
    private XmlCondition xmlCondition;

    public XmlCondition getXmlCondition() {
        return xmlCondition;
    }

    public void setXmlCondition(XmlCondition xmlCondition) {
        this.xmlCondition = xmlCondition;
    }
}
