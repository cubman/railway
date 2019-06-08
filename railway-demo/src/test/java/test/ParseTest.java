package test;

import org.junit.Assert;
import org.junit.Test;
import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.model.element.Ls;
import ru.dstu.railway.model.element.St;
import test.base.BaseTest;

import java.util.List;

import static ru.dstu.railway.api.constant.Constant.ST_MINUS;
import static ru.dstu.railway.api.constant.Constant.ST_PLUS;


public class ParseTest extends BaseTest {

    @Test
    public void testPolygonParse() {
        List<IArea> areas = polygon.getAreas();

        Assert.assertEquals(5, areas.size());

        areas.forEach(area -> area.getElements().values().forEach(element -> {
                    if (element instanceof Ls) {
                        return;
                    }

                    checkLink(area, element);

                    if (element instanceof St) {
                        element.setState(area, ST_MINUS);

                        checkLink(area, element);

                        element.setState(area, ST_PLUS);
                    }
                })
        );
    }

    private void checkLink(IArea area, IStationElement element) {

        class TestElement {
            private IStationElement baseElement, element;
            private boolean b;

            private TestElement(IStationElement element, IStationElement baseElement, boolean b) {
                this.element = element;
                this.baseElement = baseElement;
                this.b = b;
            }

            private IStationElement first() {
                return b ? element.getEven() : element.getOdd();
            }

            private IStationElement second() {
                return b ? element.getOdd() : element.getEven();
            }

            private void check() {
                if (!(element instanceof Ls)) {
                    if (element instanceof St) {
                        if (area.isPartyChanges(baseElement, element)) {
                            if (!baseElement.equals(first())) {
                                element.setState(area, ST_MINUS);
                                Assert.assertEquals(baseElement, first());
                                element.setState(area, ST_PLUS);
                                return;
                            }
                        } else if (!baseElement.equals(second())) {
                            element.setState(area, ST_MINUS);
                            Assert.assertEquals(baseElement, second());
                            element.setState(area, ST_PLUS);
                            return;
                        }
                    }
                    Assert.assertEquals(baseElement, second());
                }
            }
        }

        new TestElement(element.getEven(), element, true).check();
        new TestElement(element.getOdd(), element, false).check();
    }

    @Test
    public void testRulesParse() {
        rules.size();
    }
}
