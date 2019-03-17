package ru.dstu.railway.parse.logcon.function;

import ru.dstu.railway.rule.function.IFunction;
import ru.dstu.railway.rule.function.IFunctionResult;

import java.util.function.Supplier;

/**
 * Элементарная функция обработки результата
 */
public class Simple implements IFunction {

    private Supplier<IFunctionResult<Boolean>> supplier;

    public Simple(Supplier<IFunctionResult<Boolean>> supplier) {
        this.supplier = supplier;
    }

    @Override
    public IFunctionResult<Boolean> check() {
        return supplier.get();
    }
}
