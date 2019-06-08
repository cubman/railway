package test.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dstu.railway.api.state.IStateSender;

@Configuration
public class TestConfig {
    @Bean
    IStateSender stateSender() {
        return org.mockito.Mockito.mock(IStateSender.class);
    }
}
