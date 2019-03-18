package ru.dstu.railway.parse.rule.function;

import ru.dstu.railway.rule.function.IFunction;
import ru.dstu.railway.rule.function.IFunctionResult;
import ru.dstu.railway.parse.rule.function.description.FunctionResult;

import java.util.List;

/**
 * Условие И
 */
public class And implements IFunction {

    private List<IFunction> andCondition;

    public And(List<IFunction> function) {
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
