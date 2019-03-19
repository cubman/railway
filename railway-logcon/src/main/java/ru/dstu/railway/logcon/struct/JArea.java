package ru.dstu.railway.logcon.struct;

import java.util.List;

public class JArea {
    private List<JElement> elements;
    private String code;
    private String esr;

    public JArea(String code, String esr, List<JElement> elements) {
        this.code = code;
        this.esr = esr;
        this.elements = elements;
    }

    public List<JElement> getElements() {
        return elements;
    }

    public String getCode() {
        return code;
    }

    public String getEsr() {
        return esr;
    }
}
