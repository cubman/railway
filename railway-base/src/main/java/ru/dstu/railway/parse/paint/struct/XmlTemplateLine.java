package ru.dstu.railway.parse.paint.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlTemplateLine {
    @JacksonXmlProperty(isAttribute=true, localName="id")
    private Integer id;

    @JacksonXmlProperty(isAttribute=true, localName="link")
    private Integer link;

    @JacksonXmlProperty(isAttribute=true, localName="rotate")
    private String rotate;

    @JacksonXmlProperty(isAttribute=true, localName="pos")
    private String pos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLink() {
        return link;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
