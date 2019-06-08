package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.message.Message;
import ru.dstu.railway.api.message.MessageLevel;
import ru.dstu.railway.demo.config.DemoConfig;
import test.base.BaseTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static ru.dstu.railway.base.parse.rule.function.description.ErrorCodes.ELSE_UNDEFINED;

public class StateTest extends BaseTest {
    @Test
    public void testState() throws InterruptedException {
        messageHolder.clear(MessageLevel.ERROR);
        Assert.assertEquals(0, messageHolder.getMessages(MessageLevel.ERROR).size());
        IArea area = polygon.getAreaByCode("SplitPoint.A");
        IStationElement st5 = area.getElementByCode("СТ5");
        st5.setState(area, 1);
        TimeUnit.SECONDS.sleep(1);

        List<Message> messages = messageHolder.getMessages(MessageLevel.ERROR);
        Assert.assertEquals(1, messages.size());
        Assert.assertEquals(ELSE_UNDEFINED, messages.get(0).getCode());
    }
}
