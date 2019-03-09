package ru.dstu.railway.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dstu.railway.area.IArea;
import ru.dstu.railway.config.TestConfig;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.element.Ls;
import ru.dstu.railway.element.St;
import ru.dstu.railway.polygon.IPolygon;

import java.util.List;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ParseTest {

    @Autowired
    private IPolygon polygon;

    @Test
    public void testParse() {
        List<IArea> areas = polygon.getAreas();

        Assert.assertEquals(5, areas.size());

        for (IArea area : areas) {
            for (IStationElement element : area.getElements().values()) {
                if (element instanceof Ls ) {
                    continue;
                }

                IStationElement elementEven = element.getEven();
                IStationElement elementOdd = element.getOdd();

                if (!(elementEven instanceof Ls)) {
                    if (elementEven instanceof St) {
                        if (!element.equals(elementEven.getOdd())) {
                            elementEven.setState(1);
                            Assert.assertEquals(element, elementEven.getOdd());
                            elementEven.setState(0);
                            continue;
                        }
                    }
                    Assert.assertEquals(element, elementEven.getOdd());
                }

                if (!(elementOdd instanceof Ls)) {
                    if (elementOdd instanceof St) {
                        if (!element.equals(elementOdd.getEven())) {
                            elementOdd.setState(1);
                            Assert.assertEquals(element, elementOdd.getEven());
                            elementOdd.setState(0);
                            continue;
                        }
                    }
                    Assert.assertEquals(element, elementOdd.getEven());
                }
            }
        }
    }
}