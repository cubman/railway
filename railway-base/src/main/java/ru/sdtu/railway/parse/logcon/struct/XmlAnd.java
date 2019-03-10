package ru.sdtu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class XmlAnd implements XmlIFunction {
    @JacksonXmlProperty(localName = "condition")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlCondition> xmlConditions;

    public List<XmlCondition> getXmlConditions() {
        return xmlConditions;
    }

    public void setXmlConditions(List<XmlCondition> xmlConditions) {
        this.xmlConditions = xmlConditions;
    }
}
