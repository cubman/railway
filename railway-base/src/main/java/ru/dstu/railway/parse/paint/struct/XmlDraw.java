package ru.dstu.railway.parse.paint.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class XmlDraw {
    @JacksonXmlProperty(localName = "area")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlAreaPaint> xmlAreaPaints;

    public List<XmlAreaPaint> getXmlAreaPaints() {
        return xmlAreaPaints;
    }

    public void setXmlAreaPaints(List<XmlAreaPaint> xmlAreaPaints) {
        this.xmlAreaPaints = xmlAreaPaints;
    }
}
