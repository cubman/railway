package ru.dstu.railway.parse.paint.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlLine {
    @JacksonXmlProperty(isAttribute=true, localName="id")
    private Integer id;

    @JacksonXmlProperty(isAttribute=true, localName="x")
    private Double x;

    @JacksonXmlProperty(isAttribute=true, localName="y")
    private Double y;

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

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
