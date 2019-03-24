package ru.dstu.railway.base.parse.rule;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dstu.railway.api.message.IMessageHolder;
import ru.dstu.railway.api.message.MessageLevel;
import ru.dstu.railway.api.rule.IRule;
import ru.dstu.railway.api.rule.function.IFunction;
import ru.dstu.railway.api.rule.function.IFunctionResult;

import java.util.logging.Logger;

public class Rule implements IRule {

    private static final Logger LOGGER = Logger.getLogger(Rule.class.getName());

    private String name;
    private IFunction checkFunction;
    private IFunction executeFunction;
    private IMessageHolder messageHolder;

    Rule(String name, IFunction checkFunction, IFunction executeFunction, IMessageHolder messageHolder) {
        this.name = name;
        this.checkFunction = checkFunction;
        this.executeFunction = executeFunction;
        this.messageHolder = messageHolder;
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
            LOGGER.info("Правило: '" + name + "' успешно прошло проверку");
        } else {
            check.getErrors().forEach(iFunctionError ->
                    messageHolder.addMessage(iFunctionError.getCode(),
                            iFunctionError.getDescription(), MessageLevel.ERROR));
        }
    }
}
