package ru.dstu.railway.parse.polygon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlUp {
    @JacksonXmlProperty(isAttribute=true, localName="code")
    private String code;

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
