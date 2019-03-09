package ru.sdtu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlRule {
    @JacksonXmlProperty(isAttribute=true, localName="name")
    private String name;

    @JacksonXmlProperty(isAttribute=true, localName="group")
    private String group;

    @JacksonXmlProperty(localName="simple")
    private XmlSimple xmlSimple;

    @JacksonXmlProperty(localName="print")
    private XmlPrint xmlPrint;

    @JacksonXmlProperty(localName="if")
    private XmlIf xmlIf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

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
