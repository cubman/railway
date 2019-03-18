package ru.dstu.railway.parse.rule;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dstu.railway.message.IMessageHolder;
import ru.dstu.railway.rule.IRule;
import ru.dstu.railway.rule.function.IFunction;
import ru.dstu.railway.rule.function.IFunctionResult;

import java.util.logging.Logger;

public class Rule implements IRule {

    private static final Logger LOGGER = Logger.getLogger(Rule.class.getName());

    @Autowired
    private IMessageHolder messageHolder;

    private String name;
    private IFunction checkFunction;
    private IFunction executeFunction;

    Rule(String name, IFunction checkFunction, IFunction executeFunction) {
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
        IFunctionResult<Boolean> check = executeFunction.check();

        if (check.getResult()) {
            LOGGER.info("Правило: " + name + "успешно прошло проверку");
        } else {
            check.getErrors().forEach(iFunctionError ->
                    messageHolder.addMessage(iFunctionError.getDescription()));
        }
    }
}
