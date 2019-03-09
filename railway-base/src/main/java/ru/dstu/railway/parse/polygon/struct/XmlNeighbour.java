package ru.dstu.railway.parse.polygon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlNeighbour {
    @JacksonXmlProperty(isAttribute=true, localName="linkArea")
    private String linkArea;

    @JacksonXmlProperty(isAttribute=true, localName="linkObject")
    private String linkObject;

    public String getLinkArea() {
        return linkArea;
    }

    public void setLinkArea(String linkArea) {
        this.linkArea = linkArea;
    }

    public String getLinkObject() {
        return linkObject;
    }

    public void setLinkObject(String linkObject) {
        this.linkObject = linkObject;
    }
}
