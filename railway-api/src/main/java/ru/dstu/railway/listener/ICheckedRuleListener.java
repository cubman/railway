package ru.dstu.railway.listener;

import ru.dstu.railway.rule.IRule;

import java.util.List;

public interface ICheckedRuleListener {

    /**
     * Уведомить о прошедших условия правилах
     */
    void notify(List<IRule> uncheckedRules);
}
