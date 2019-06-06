package ru.dstu.railway.base.parse.rule.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public abstract class XmlAbstractSimpleFunction implements XmlIFunction {
    @JacksonXmlProperty(localName = "check")
    private XmlCheck xmlCheck;

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

    @JacksonXmlProperty(localName = "not")
    private XmlNot xmlNot;

    @JacksonXmlProperty(localName = "sound")
    private XmlSound xmlSound;

    public XmlCheck getXmlCheck() {
        return xmlCheck;
    }

    public void setXmlCheck(XmlCheck xmlCheck) {
        this.xmlCheck = xmlCheck;
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

    public XmlNot getXmlNot() {
        return xmlNot;
    }

    public void setXmlNot(XmlNot xmlNot) {
        this.xmlNot = xmlNot;
    }

    public XmlSound getXmlSound() {
        return xmlSound;
    }

    public void setXmlSound(XmlSound xmlSound) {
        this.xmlSound = xmlSound;
    }
}
