package ru.dstu.railway.logcon.struct;

import java.util.List;

public class JOwnArea {
    private String code;
    private String esr;

    public JOwnArea(String code, String esr) {
        this.code = code;
        this.esr = esr;
    }

    public String getCode() {
        return code;
    }

    public String getEsr() {
        return esr;
    }
}
