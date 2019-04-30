package ru.dstu.railway.paint.parse.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class XmlElementPaint {
    @JacksonXmlProperty(localName = "type")
    private String type;

    @JacksonXmlProperty(isAttribute = true, localName = "code")
    protected String code;

    @JacksonXmlProperty(localName = "version")
    private Integer version;

    @JacksonXmlProperty(localName = "back")
    private Boolean back;

    @JacksonXmlProperty(localName = "figure")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlFigure> xmlFigures;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<XmlFigure> getXmlFigures() {
        return xmlFigures;
    }

    public void setXmlFigures(List<XmlFigure> xmlFigures) {
        this.xmlFigures = xmlFigures;
    }

    public Boolean getBack() {
        return back;
    }

    public void setBack(Boolean back) {
        this.back = back;
    }
}
