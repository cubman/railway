package ru.dstu.railway.base.parse.polygon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AbstractXmlElement {
    @JacksonXmlProperty(isAttribute=true, localName="code")
    protected String code;

    @JacksonXmlProperty(isAttribute=true, localName="evenArea")
    private String evenArea;

    @JacksonXmlProperty(isAttribute=true, localName="oddArea")
    private String oddArea;

    @JacksonXmlProperty(isAttribute=true, localName="evenLink")
    private String evenLink;

    @JacksonXmlProperty(isAttribute=true, localName="oddLink")
    private String oddLink;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEvenArea() {
        return evenArea;
    }

    public void setEvenArea(String evenArea) {
        this.evenArea = evenArea;
    }

    public String getOddArea() {
        return oddArea;
    }

    public void setOddArea(String oddArea) {
        this.oddArea = oddArea;
    }

    public String getEvenLink() {
        return evenLink;
    }

    public void setEvenLink(String evenLink) {
        this.evenLink = evenLink;
    }

    public String getOddLink() {
        return oddLink;
    }

    public void setOddLink(String oddLink) {
        this.oddLink = oddLink;
    }
}
