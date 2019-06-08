package ru.dstu.railway.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dstu.railway.demo.Actions;

@Configuration
public class DemoConfig {


    @Bean
    Actions actions() {
        return new Actions();
    }

}
