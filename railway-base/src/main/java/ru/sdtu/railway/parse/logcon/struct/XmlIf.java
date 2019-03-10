package ru.sdtu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlIf extends AbstractXmlFunction{

    @JacksonXmlProperty(localName="else")
    private XmlElse xmlElse;

    public XmlElse getXmlElse() {
        return xmlElse;
    }

    public void setXmlElse(XmlElse xmlElse) {
        this.xmlElse = xmlElse;
    }
}
