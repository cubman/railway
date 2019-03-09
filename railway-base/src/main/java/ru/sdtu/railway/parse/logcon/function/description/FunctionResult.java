package ru.sdtu.railway.parse.logcon.function.description;

import ru.dstu.railway.rule.function.IFunctionError;
import ru.dstu.railway.rule.function.IFunctionResult;

import java.util.ArrayList;
import java.util.List;

public class FunctionResult implements IFunctionResult<Boolean> {

    private Boolean result;
    private List<IFunctionError> errors;

    public FunctionResult(Boolean result) {
        this.result = result;
        errors = new ArrayList<>();
    }

    public FunctionResult(Boolean result, List<IFunctionError> errors) {
        this.result = result;
        this.errors = errors;
    }

    @Override
    public Boolean getResult() {
        return result;
    }

    @Override
    public List<IFunctionError> getErrors() {
        return errors;
    }
}
