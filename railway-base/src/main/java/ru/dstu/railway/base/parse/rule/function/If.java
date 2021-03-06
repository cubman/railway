package ru.dstu.railway.base.parse.rule.function;

import ru.dstu.railway.api.rule.function.IFunction;
import ru.dstu.railway.api.rule.function.IFunctionResult;
import ru.dstu.railway.base.parse.rule.function.description.FunctionError;
import ru.dstu.railway.base.parse.rule.function.description.FunctionResult;

import java.util.Collections;

import static ru.dstu.railway.base.parse.rule.function.description.ErrorCodes.ELSE_UNDEFINED;

/**
 * Условие ветвления
 */
public class If implements IFunction {

    private IFunction ifFunction;
    private IFunction thenFunction;
    private IFunction elseFunction;

    public If(IFunction ifFunction, IFunction thenFunction, IFunction elseFunction) {
        this.ifFunction = ifFunction;
        this.thenFunction = thenFunction;
        this.elseFunction = elseFunction;
    }

    @Override
    public IFunctionResult<Boolean> check() {
        IFunctionResult<Boolean> check = ifFunction.check();

        if (check.getResult()) {
            return thenFunction.check();
        } else {
            return elseFunction != null ?
                    elseFunction.check() :
                    new FunctionResult(Boolean.FALSE,
                            Collections.singletonList(
                                    new FunctionError(ELSE_UNDEFINED, "условие по else не определено")));
        }
    }
}