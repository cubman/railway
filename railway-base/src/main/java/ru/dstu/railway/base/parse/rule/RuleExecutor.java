package ru.dstu.railway.base.parse.rule;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RuleExecutor {
    private static final int THREAD_COUNT = 10;

    private final ExecutorService service;

    RuleExecutor() {
        this.service = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    void execute(Runnable rule) {
        service.submit(rule);
    }

    @EventListener(classes = ContextClosedEvent.class)
    public void after() {
        service.shutdown();
    }
}
