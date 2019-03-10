package ru.sdtu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlSimple implements XmlIFunction {

    @JacksonXmlProperty(isAttribute=true, localName="code")
    private String code;

    @JacksonXmlProperty(isAttribute=true, localName="state")
    private Integer state;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
