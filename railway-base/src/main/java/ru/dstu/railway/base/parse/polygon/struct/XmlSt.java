package ru.dstu.railway.base.parse.polygon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlSt extends AbstractXmlElement {

    @JacksonXmlProperty(isAttribute=true, localName="evenLinkP")
    private String evenLinkP;

    @JacksonXmlProperty(isAttribute=true, localName="evenLinkM")
    private String evenLinkM;

    @JacksonXmlProperty(isAttribute=true, localName="oddLinkP")
    private String oddLinkP;

    @JacksonXmlProperty(isAttribute=true, localName="oddLinkM")
    private String oddLinkM;

    public String getEvenLinkP() {
        return evenLinkP;
    }

    public void setEvenLinkP(String evenLinkP) {
        this.evenLinkP = evenLinkP;
    }

    public String getEvenLinkM() {
        return evenLinkM;
    }

    public void setEvenLinkM(String evenLinkM) {
        this.evenLinkM = evenLinkM;
    }

    public String getOddLinkP() {
        return oddLinkP;
    }

    public void setOddLinkP(String oddLinkP) {
        this.oddLinkP = oddLinkP;
    }

    public String getOddLinkM() {
        return oddLinkM;
    }

    public void setOddLinkM(String oddLinkM) {
        this.oddLinkM = oddLinkM;
    }
}
