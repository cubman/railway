package ru.dstu.railway.parse.rule.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlPrint implements XmlIFunction {
    @JacksonXmlProperty(isAttribute=true, localName="text")
    private String text;

    @JacksonXmlProperty(isAttribute=true, localName="out")
    private String out;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }
}
