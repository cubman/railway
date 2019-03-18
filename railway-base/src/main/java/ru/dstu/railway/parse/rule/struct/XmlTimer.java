package ru.dstu.railway.parse.rule.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlTimer implements XmlIFunction {

    @JacksonXmlProperty(isAttribute=true, localName="sleep")
    private int sleep;

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }
}
