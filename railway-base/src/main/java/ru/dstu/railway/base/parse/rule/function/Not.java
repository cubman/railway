package ru.dstu.railway.base.parse.rule.function;

import ru.dstu.railway.api.rule.function.IFunction;
import ru.dstu.railway.api.rule.function.IFunctionResult;
import ru.dstu.railway.base.parse.rule.function.description.ErrorCodes;
import ru.dstu.railway.base.parse.rule.function.description.FunctionError;
import ru.dstu.railway.base.parse.rule.function.description.FunctionResult;

import java.util.Collections;

public class Not implements IFunction {
    private IFunction function;

    public Not(IFunction function) {
        this.function = function;
    }

    @Override
    public IFunctionResult<Boolean> check() {
        IFunctionResult<Boolean> check = function.check();

        if (!check.getResult()) {
            return new FunctionResult(Boolean.TRUE);
        } else {
            return new FunctionResult(Boolean.FALSE, Collections.singletonList(new FunctionError(ErrorCodes.NOT_CHECK,
                    "Условие было реверсировано")));
        }
    }
}
