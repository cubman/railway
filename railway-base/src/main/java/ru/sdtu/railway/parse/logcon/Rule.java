package ru.sdtu.railway.parse.logcon;

import ru.dstu.railway.rule.IRule;
import ru.dstu.railway.rule.function.IFunction;

import java.util.function.Consumer;

public class Rule implements IRule {

    private IFunction checkFunction;
    private IFunction executeFunction;

    public Rule(IFunction checkFunction, IFunction executeFunction) {
        this.checkFunction = checkFunction;
        this.executeFunction = executeFunction;
    }

    @Override
    public boolean check() {
        return checkFunction.check().getResult();
    }

    @Override
    public void execute(Consumer<IFunction> executor) {
        executor.accept(executeFunction);
    }
}
