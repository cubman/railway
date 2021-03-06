package ru.dstu.railway.paint.parse.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlFigure {
    @JacksonXmlProperty(isAttribute=true, localName="type")
    private String type;

    @JacksonXmlProperty(isAttribute=true, localName="id")
    private Integer id;

    @JacksonXmlProperty(isAttribute=true, localName="x")
    private Double x;

    @JacksonXmlProperty(isAttribute=true, localName="y")
    private Double y;

    @JacksonXmlProperty(isAttribute=true, localName="length")
    private Integer length;

    @JacksonXmlProperty(isAttribute=true, localName="description")
    private String description;

    @JacksonXmlProperty(localName = "plus")
    private Boolean plus;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPlus() {
        return plus;
    }

    public void setPlus(Boolean plus) {
        this.plus = plus;
    }
}
