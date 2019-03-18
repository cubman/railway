package ru.dstu.railway.parse.polygon.struct;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class XmlArea extends AbstractXmlElement {

    @JacksonXmlProperty(isAttribute=true, localName="type")
    private String type;

    @JacksonXmlProperty(isAttribute=true, localName="esr")
    private String esr;

    @JacksonXmlProperty(localName = "kp")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlKp> kps;

    @JacksonXmlProperty(localName = "st")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlSt> sts;

    @JacksonXmlProperty(localName = "sv")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlSv> svs;

    @JacksonXmlProperty(localName = "up")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlUp> ups;

    @JacksonXmlProperty(localName = "party")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<XmlParty> parties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEsr() {
        return esr;
    }

    public void setEsr(String esr) {
        this.esr = esr;
    }

    public List<XmlKp> getKps() {
        return kps;
    }

    public void setKps(List<XmlKp> kps) {
        this.kps = kps;
    }

    public List<XmlSt> getSts() {
        return sts;
    }

    public void setSts(List<XmlSt> sts) {
        this.sts = sts;
    }

    public List<XmlSv> getSvs() {
        return svs;
    }

    public void setSvs(List<XmlSv> svs) {
        this.svs = svs;
    }

    public List<XmlUp> getUps() {
        return ups;
    }

    public void setUps(List<XmlUp> ups) {
        this.ups = ups;
    }

    public List<XmlParty> getParties() {
        return parties;
    }

    public void setParties(List<XmlParty> parties) {
        this.parties = parties;
    }
}
