package ru.sdtu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlPrint {
    @JacksonXmlProperty(isAttribute=true, localName="text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
