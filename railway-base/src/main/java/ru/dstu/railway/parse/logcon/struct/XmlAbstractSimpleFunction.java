package ru.dstu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public abstract class XmlAbstractSimpleFunction implements XmlIFunction {
    @JacksonXmlProperty(localName = "simple")
    private XmlSimple xmlSimple;

    @JacksonXmlProperty(localName = "print")
    private XmlPrint xmlPrint;

    @JacksonXmlProperty(localName = "if")
    private XmlIf xmlIf;

    @JacksonXmlProperty(localName = "and")
    private XmlAnd xmlAnd;

    @JacksonXmlProperty(localName = "or")
    private XmlOr xmlOr;

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

    public XmlAnd getXmlAnd() {
        return xmlAnd;
    }

    public void setXmlAnd(XmlAnd xmlAnd) {
        this.xmlAnd = xmlAnd;
    }

    public XmlOr getXmlOr() {
        return xmlOr;
    }

    public void setXmlOr(XmlOr xmlOr) {
        this.xmlOr = xmlOr;
    }
}
