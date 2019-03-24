package ru.dstu.railway.base.parse.rule.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public abstract class XmlAbstractSimpleFunction implements XmlIFunction {
    @JacksonXmlProperty(localName = "simple")
    private XmlSimple xmlSimple;

    @JacksonXmlProperty(localName = "timer")
    private XmlTimer xmlTimer;

    @JacksonXmlProperty(localName = "print")
    private XmlPrint xmlPrint;

    @JacksonXmlProperty(localName = "setstate")
    private XmlSetState xmlSetState;

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

    public XmlTimer getXmlTimer() {
        return xmlTimer;
    }

    public void setXmlTimer(XmlTimer xmlTimer) {
        this.xmlTimer = xmlTimer;
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

    public XmlSetState getXmlSetState() {
        return xmlSetState;
    }

    public void setXmlSetState(XmlSetState xmlSetState) {
        this.xmlSetState = xmlSetState;
    }
}
