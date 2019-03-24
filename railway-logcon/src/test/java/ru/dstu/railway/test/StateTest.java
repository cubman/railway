package ru.dstu.railway.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dstu.railway.api.constant.Pair;
import ru.dstu.railway.api.message.IMessageHolder;
import ru.dstu.railway.api.message.MessageLevel;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.polygon.IPolygon;
import ru.dstu.railway.base.config.RailwayPolygonConfig;
import ru.dstu.railway.logcon.config.LogConConfig;
import ru.dstu.railway.test.base.BaseTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static ru.dstu.railway.base.parse.rule.function.description.ErrorCodes.ELSE_UNDEFINED;

@ContextConfiguration(classes = LogConConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:test.properties")
public class StateTest extends BaseTest {
    @Test
    public void testState() throws InterruptedException {
        messageHolder.clear(MessageLevel.ERROR);
        Assert.assertEquals(0, messageHolder.getMessages(MessageLevel.ERROR).size());
        IStationElement st5 = polygon.getElementByAreaAndCode("SplitPoint.A", "СТ5");
        st5.setState(1);
        TimeUnit.SECONDS.sleep(1);

        List<Pair<String, String>> messages = messageHolder.getMessages(MessageLevel.ERROR);
        Assert.assertEquals(1, messages.size());
        Assert.assertEquals(ELSE_UNDEFINED, messages.get(0).getT1());
    }
}
