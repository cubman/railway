package ru.sdtu.railway.parse.logcon.function;

import ru.dstu.railway.rule.function.IFunction;
import ru.dstu.railway.rule.function.IFunctionResult;
import ru.sdtu.railway.parse.logcon.function.description.FunctionResult;

/**
 * Условие И
 */
public class And implements IFunction {

    private IFunction[] andCondition;

    public And(IFunction... function) {
        andCondition = function;
    }

    @Override
    public IFunctionResult<Boolean> check() {
        for (IFunction function : andCondition) {
            IFunctionResult<Boolean> check = function.check();
            if (!check.getResult()) {
                return check;
            }
        }
        return new FunctionResult(Boolean.TRUE);
    }
}
