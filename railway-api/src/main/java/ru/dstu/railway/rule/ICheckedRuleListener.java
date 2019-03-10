package ru.dstu.railway.rule;

import java.util.List;

public interface ICheckedRuleListener {

    /**
     * Уведомить о прошедших условия правилах
     */
    void notify(List<IRule> uncheckedRules);
}
