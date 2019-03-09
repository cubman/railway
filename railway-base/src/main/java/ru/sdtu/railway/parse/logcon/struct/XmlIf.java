package ru.sdtu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlIf {
    @JacksonXmlProperty(localName="simple")
    private XmlSimple xmlSimple;

    @JacksonXmlProperty(localName="print")
    private XmlPrint xmlPrint;

    public XmlSimple getXmlSimple() {
        return xmlSimple;
    }

    public void setXmlSimple(XmlSimple xmlSimple) {
        this.xmlSimple = xmlSimple;
    }

    public XmlPrint getXmlPrint() {
        return xmlPrint;
    }

    public void setXmlPrint(XmlPrint xmlPrint) {
        this.xmlPrint = xmlPrint;
    }
}
