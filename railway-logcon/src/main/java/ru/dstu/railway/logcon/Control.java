package ru.dstu.railway.logcon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.dstu.railway.rule")
public class Control {
    public static void main(String[] args) {
        SpringApplication.run(Control.class, args);
    }
}
