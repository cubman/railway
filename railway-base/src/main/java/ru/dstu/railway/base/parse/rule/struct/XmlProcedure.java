package ru.dstu.railway.base.parse.rule.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlProcedure implements XmlIFunction {

    @JacksonXmlProperty(localName = "class")
    private String packageClass;

    @JacksonXmlProperty(localName = "method")
    private String method;


    public String getPackageClass() {
        return packageClass;
    }

    public String getMethod() {
        return method;
    }
}
