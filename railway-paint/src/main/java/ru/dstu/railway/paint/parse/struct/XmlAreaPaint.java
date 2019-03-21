package ru.dstu.railway.paint.parse.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class XmlAreaPaint {
    @JacksonXmlProperty(localName = "element")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlElementPaint> xmlElementPaints;

    @JacksonXmlProperty(isAttribute = true, localName = "code")
    protected String code;

    @JacksonXmlProperty(isAttribute = true, localName = "esr")
    private String esr;

    public List<XmlElementPaint> getXmlElementPaints() {
        return xmlElementPaints;
    }

    public void setXmlElementPaints(List<XmlElementPaint> xmlElementPaints) {
        this.xmlElementPaints = xmlElementPaints;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEsr() {
        return esr;
    }

    public void setEsr(String esr) {
        this.esr = esr;
    }
}
