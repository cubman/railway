package ru.dstu.railway.base.parse.polygon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlParty {
    @JacksonXmlProperty(isAttribute=true, localName="codeFirst")
    private String firstElement;

    @JacksonXmlProperty(isAttribute=true, localName="codeSecond")
    private String secondElement;

    public String getFirstElement() {
        return firstElement;
    }

    public void setFirstElement(String firstElement) {
        this.firstElement = firstElement;
    }

    public String getSecondElement() {
        return secondElement;
    }

    public void setSecondElement(String secondElement) {
        this.secondElement = secondElement;
    }
}
