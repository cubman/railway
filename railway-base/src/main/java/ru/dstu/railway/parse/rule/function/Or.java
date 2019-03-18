package ru.dstu.railway.parse.rule.function;

import ru.dstu.railway.rule.function.IFunction;
import ru.dstu.railway.rule.function.IFunctionResult;
import ru.dstu.railway.parse.rule.function.description.ErrorCodes;
import ru.dstu.railway.parse.rule.function.description.FunctionError;
import ru.dstu.railway.parse.rule.function.description.FunctionResult;

import java.util.Collections;
import java.util.List;

/**
 * Условие ИЛИ
 */
public class Or implements IFunction {

    private List<IFunction> orCondition;

    public Or(List<IFunction> function) {
        orCondition = function;
    }

    @Override
    public IFunctionResult<Boolean> check() {
        for (IFunction function : orCondition) {
            IFunctionResult<Boolean> check = function.check();
            if (check.getResult()) {
                return check;
            }
        }
        return new FunctionResult(Boolean.FALSE,
                Collections.singletonList(new FunctionError(ErrorCodes.NO_ELEMENTS,
                        "По условию Or не найдено элментов, удовлетворяющих условию")));
    }
}