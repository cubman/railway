package ru.dstu.railway.parse.paint.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class XmlTemplateElementPaint {
    @JacksonXmlProperty(localName = "type")
    private String type;

    @JacksonXmlProperty(localName = "version")
    private Integer version;

    @JacksonXmlProperty(localName = "line")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlTemplateLine> xmlTemplateLines;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<XmlTemplateLine> getXmlTemplateLines() {
        return xmlTemplateLines;
    }

    public void setXmlTemplateLines(List<XmlTemplateLine> xmlTemplateLines) {
        this.xmlTemplateLines = xmlTemplateLines;
    }
}
