package ru.dstu.railway.parse.logcon;

import ru.dstu.railway.rule.IRule;
import ru.dstu.railway.rule.function.IFunction;

import java.util.function.Consumer;

public class Rule implements IRule {

    private String name;
    private IFunction checkFunction;
    private IFunction executeFunction;

    public Rule(String name, IFunction checkFunction, IFunction executeFunction) {
        this.name = name;
        this.checkFunction = checkFunction;
        this.executeFunction = executeFunction;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean check() {
        return checkFunction.check().getResult();
    }

    @Override
    public void execute() {
        executeFunction.check();
    }
}
