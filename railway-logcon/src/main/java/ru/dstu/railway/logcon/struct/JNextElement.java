package ru.dstu.railway.logcon.struct;

import java.util.List;

public class JNextElement {
    private String code;
    private String type;
    private List<JOwnArea> ownAreas;

    public JNextElement(String code, String type, List<JOwnArea> ownAreas) {
        this.code = code;
        this.type = type;
        this.ownAreas = ownAreas;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public List<JOwnArea> getOwnAreas() {
        return ownAreas;
    }
}
