package ru.dstu.railway.api.listener;

import ru.dstu.railway.api.rule.IRule;

import java.util.List;

public interface ICheckedRuleListener {

    /**
     * Уведомить о прошедших условия правилах
     */
    void notify(List<IRule> uncheckedRules);
}
