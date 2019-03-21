package ru.dstu.railway.paint.parse.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlPaint {
    @JacksonXmlProperty(localName = "base")
    private XmlBasePaint xmlBase;

    @JacksonXmlProperty(localName = "draw")
    private XmlDraw xmlDraw;

    public XmlBasePaint getXmlBase() {
        return xmlBase;
    }

    public void setXmlBase(XmlBasePaint xmlBase) {
        this.xmlBase = xmlBase;
    }

    public XmlDraw getXmlDraw() {
        return xmlDraw;
    }

    public void setXmlDraw(XmlDraw xmlDraw) {
        this.xmlDraw = xmlDraw;
    }
}
