package ru.dstu.railway.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.logcon.config.LogConConfig;
import ru.dstu.railway.model.element.Ls;
import ru.dstu.railway.model.element.St;
import ru.dstu.railway.base.config.RailwayPolygonConfig;
import ru.dstu.railway.api.polygon.IPolygon;
import ru.dstu.railway.api.rule.IRule;
import ru.dstu.railway.test.base.BaseTest;

import java.util.List;

import static ru.dstu.railway.api.constant.Constant.ST_MINUS;
import static ru.dstu.railway.api.constant.Constant.ST_PLUS;


public class ParseTest extends BaseTest {

    @Test
    public void testPolygonParse() {
        List<IArea> areas = polygon.getAreas();

        Assert.assertEquals(5, areas.size());

        for (IArea area : areas) {
            for (IStationElement element : area.getElements().values()) {
                if (element instanceof Ls) {
                    continue;
                }

                checkLink(area, element);

                if (element instanceof St) {
                    element.setState(ST_MINUS);

                    checkLink(area, element);

                    element.setState(ST_PLUS);
                }
            }
        }
    }

    private void checkLink(IArea area, IStationElement element) {
        IStationElement elementEven = element.getEven();
        IStationElement elementOdd = element.getOdd();

        if (!(elementEven instanceof Ls)) {
            if (elementEven instanceof St) {
                if (area.isPartyChanges(element, elementEven)) {
                    if (!element.equals(elementEven.getEven())) {
                        elementEven.setState(ST_MINUS);
                        Assert.assertEquals(element, elementEven.getEven());
                        elementEven.setState(ST_PLUS);
                        return;
                    }
                } else if (!element.equals(elementEven.getOdd())) {
                    elementEven.setState(ST_MINUS);
                    Assert.assertEquals(element, elementEven.getOdd());
                    elementEven.setState(ST_PLUS);
                    return;
                }

                Assert.assertEquals(element, elementEven.getOdd());
            }
        }

        if (!(elementOdd instanceof Ls)) {
            if (elementOdd instanceof St) {
                if (area.isPartyChanges(element, elementOdd)) {
                    if (!element.equals(elementOdd.getOdd())) {
                        elementOdd.setState(ST_MINUS);
                        Assert.assertEquals(element, elementOdd.getOdd());
                        elementOdd.setState(ST_PLUS);
                        return;
                    }
                } else if (!element.equals(elementOdd.getEven())) {
                    elementOdd.setState(ST_MINUS);
                    Assert.assertEquals(element, elementOdd.getEven());
                    elementOdd.setState(ST_PLUS);
                    return;
                }
            }

            Assert.assertEquals(element, elementOdd.getEven());
        }
    }

    @Test
    public void testRulesParse() {
        rules.size();
    }
}
