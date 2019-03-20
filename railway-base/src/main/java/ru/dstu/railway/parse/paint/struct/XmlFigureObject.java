package ru.dstu.railway.parse.paint.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlFigureObject {
    @JacksonXmlProperty(isAttribute=true, localName="id")
    private Integer id;

    @JacksonXmlProperty(localName = "type")
    private String type;

    @JacksonXmlProperty(isAttribute=true, localName="link")
    private Integer link;

    @JacksonXmlProperty(isAttribute=true, localName="pos")
    private String pos;

    @JacksonXmlProperty(isAttribute=true, localName="rotate")
    private Integer rotate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLink() {
        return link;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Integer getRotate() {
        return rotate;
    }

    public void setRotate(Integer rotate) {
        this.rotate = rotate;
    }
}
