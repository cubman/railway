package ru.sdtu.railway.parse.logcon.function;

import ru.dstu.railway.rule.function.IFunction;
import ru.dstu.railway.rule.function.IFunctionResult;
import ru.sdtu.railway.parse.logcon.function.description.ErrorCodes;
import ru.sdtu.railway.parse.logcon.function.description.FunctionError;
import ru.sdtu.railway.parse.logcon.function.description.FunctionResult;

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