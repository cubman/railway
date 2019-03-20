package ru.dstu.railway.parse.paint.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class XmlBasePaint {
    @JacksonXmlProperty(localName = "element")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlTemplateElementPaint> xmlTemplateElementPaints;

    public List<XmlTemplateElementPaint> getXmlTemplateElementPaints() {
        return xmlTemplateElementPaints;
    }

    public void setXmlTemplateElementPaints(List<XmlTemplateElementPaint> xmlTemplateElementPaints) {
        this.xmlTemplateElementPaints = xmlTemplateElementPaints;
    }
}
