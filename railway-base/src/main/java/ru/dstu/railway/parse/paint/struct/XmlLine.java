package ru.dstu.railway.parse.paint.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlLine {
    @JacksonXmlProperty(isAttribute=true, localName="id")
    private Integer id;

    @JacksonXmlProperty(isAttribute=true, localName="x")
    private Integer x;

    @JacksonXmlProperty(isAttribute=true, localName="y")
    private Integer y;

    @JacksonXmlProperty(isAttribute=true, localName="length")
    private Integer length;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
