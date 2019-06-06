package ru.dstu.railway.base.parse.rule.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlSound implements XmlIFunction {

    @JacksonXmlProperty(localName = "name")
    private String name;


    public String getName() {
        return name;
    }
}
