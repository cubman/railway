package ru.sdtu.railway.parse.logcon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class XmlGroup {
    @JacksonXmlProperty(localName = "element")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlElement> elements;

    public List<XmlElement> getElements() {
        return elements;
    }

    public void setElements(List<XmlElement> elements) {
        this.elements = elements;
    }
}
