package ru.sdtu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlSimple {

    @JacksonXmlProperty(isAttribute=true, localName="instanceOf")
    private String instanceOf;

    @JacksonXmlProperty(isAttribute=true, localName="code")
    private String code;

    public String getInstanceOf() {
        return instanceOf;
    }

    public void setInstanceOf(String instanceOf) {
        this.instanceOf = instanceOf;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
