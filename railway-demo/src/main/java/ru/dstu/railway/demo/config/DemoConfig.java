package ru.dstu.railway.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.dstu.railway.demo.Actions;
import ru.dstu.railway.storage.config.StorageConfig;

@Configuration
@Import({StorageConfig.class})
public class DemoConfig {


    @Bean
    Actions actions() {
        return new Actions();
    }

}
