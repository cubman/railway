package ru.dstu.railway.parse.rule.function;

import ru.dstu.railway.rule.function.IFunction;
import ru.dstu.railway.rule.function.IFunctionResult;
import ru.dstu.railway.parse.rule.function.description.FunctionResult;

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
            return elseFunction != null ? elseFunction.check() : new FunctionResult(Boolean.FALSE);
        }
    }
}