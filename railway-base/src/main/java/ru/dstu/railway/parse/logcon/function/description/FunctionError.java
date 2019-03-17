package ru.dstu.railway.parse.logcon.function.description;

import ru.dstu.railway.rule.function.IFunctionError;

public class FunctionError implements IFunctionError {

    private String code;
    private String description;

    public FunctionError(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
