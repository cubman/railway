package test.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dstu.railway.api.message.IMessageHolder;
import ru.dstu.railway.api.polygon.IPolygon;
import ru.dstu.railway.api.rule.IRule;
import ru.dstu.railway.base.config.RailwayPolygonConfig;
import ru.dstu.railway.demo.config.DemoConfig;

import java.util.List;

@ContextConfiguration(classes = {RailwayPolygonConfig.class, DemoConfig.class, TestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:test.properties")
public class BaseTest {
    @Autowired
    protected IPolygon polygon;
    @Autowired
    protected List<IRule> rules;
    @Autowired
    protected IMessageHolder messageHolder;

    @Test
    public void defaultTest() {
        
    }
}
