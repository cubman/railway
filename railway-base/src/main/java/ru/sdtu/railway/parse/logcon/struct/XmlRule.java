package ru.sdtu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlRule extends AbstractXmlFunction {
    @JacksonXmlProperty(isAttribute=true, localName="name")
    private String name;

    @JacksonXmlProperty(isAttribute=true, localName="type")
    private String type;

    @JacksonXmlProperty(isAttribute=true, localName="area")
    private String area;

    @JacksonXmlProperty(localName="group")
    private XmlGroup xmlGroup;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public XmlGroup getXmlGroup() {
        return xmlGroup;
    }

    public void setXmlGroup(XmlGroup xmlGroup) {
        this.xmlGroup = xmlGroup;
    }
}
