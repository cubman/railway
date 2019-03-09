package ru.sdtu.railway.parse.logcon;

import ru.dstu.railway.rule.IRule;

public class Rule implements IRule {


    private Rule() {

    }

    public static Rule create() {
        return new Rule();
    }



    @Override
    public boolean check() {
        return false;
    }

    @Override
    public void execute() {

    }
}
