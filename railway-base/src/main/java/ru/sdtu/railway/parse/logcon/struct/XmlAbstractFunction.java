package ru.sdtu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public abstract class XmlAbstractFunction implements XmlIFunction {
    @JacksonXmlProperty(localName = "simple")
    private XmlSimple xmlSimple;

    @JacksonXmlProperty(localName = "print")
    private XmlPrint xmlPrint;

    @JacksonXmlProperty(localName = "if")
    private XmlIf xmlIf;


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

    public XmlIf getXmlIf() {
        return xmlIf;
    }

    public void setXmlIf(XmlIf xmlIf) {
        this.xmlIf = xmlIf;
    }

}
