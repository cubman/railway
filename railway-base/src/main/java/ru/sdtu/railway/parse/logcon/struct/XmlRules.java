package ru.sdtu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "rules")
public class XmlRules {

    @JacksonXmlProperty(localName = "rule")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlRule> xmlAreas;
}
