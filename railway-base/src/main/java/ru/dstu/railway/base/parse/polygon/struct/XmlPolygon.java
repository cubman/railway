package ru.dstu.railway.base.parse.polygon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "polygon")
public class XmlPolygon {
    @JacksonXmlProperty(localName = "area")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlArea> xmlAreas;

    public List<XmlArea> getXmlAreas() {
        return xmlAreas;
    }

    public void setXmlAreas(List<XmlArea> xmlAreas) {
        this.xmlAreas = xmlAreas;
    }
}
