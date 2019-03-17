package ru.dstu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlIf implements XmlIFunction {

    @JacksonXmlProperty(localName = "condition")
    private XmlCondition xmlCondition;

    @JacksonXmlProperty(localName = "then")
    private XmlCondition xmlThen;

    @JacksonXmlProperty(localName = "else")
    private XmlCondition xmlElse;

    public XmlCondition getXmlCondition() {
        return xmlCondition;
    }

    public void setXmlCondition(XmlCondition xmlCondition) {
        this.xmlCondition = xmlCondition;
    }

    public XmlCondition getXmlThen() {
        return xmlThen;
    }

    public void setXmlThen(XmlCondition xmlThen) {
        this.xmlThen = xmlThen;
    }

    public XmlCondition getXmlElse() {
        return xmlElse;
    }

    public void setXmlElse(XmlCondition xmlElse) {
        this.xmlElse = xmlElse;
    }
}
