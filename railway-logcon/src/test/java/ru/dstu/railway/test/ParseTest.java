package ru.dstu.railway.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dstu.railway.config.TestConfig;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ParseTest {
    @Test
    public void testParse() {
        int t = 4;
        System.out.println(t + "УРА");
    }
}
