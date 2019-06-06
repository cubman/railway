package ru.dstu.railway.logcon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.message.IMessageHolder;
import ru.dstu.railway.api.message.MessageLevel;
import ru.dstu.railway.api.polygon.IPolygon;
import ru.dstu.railway.model.element.Sv;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static ru.dstu.railway.api.constant.Constant.*;

@Controller
public class WebController {

    private static final Logger LOGGER = Logger.getLogger(WebController.class.getName());

    @Autowired
    private IPolygon polygon;
    @Autowired
    private IMessageHolder messageHolder;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("statistic/")
    public ModelAndView statistic() {
        ModelAndView modelAndView = new ModelAndView("statistic");
        modelAndView.addObject("areas", polygon.getAreas());
        modelAndView.addObject("infoMessages", messageHolder.getMessages(MessageLevel.INFO));
        modelAndView.addObject("errorMessages", messageHolder.getMessages(MessageLevel.ERROR));
        return modelAndView;
    }

    @RequestMapping("control/")
    public ModelAndView index(@RequestParam(required = false, name = "area") String area,
                              @RequestParam(required = false, name = "element") String element,
                              @RequestParam(required = false, name = "state") String state) {

        ModelAndView modelAndView = new ModelAndView("control");

        IArea areaByCode = polygon.getAreaByCode(area);
        if (areaByCode == null) {
            modelAndView.addObject("msg", area + "не найдена");
            return modelAndView;
        }

        IStationElement elementByCode = areaByCode.getElementByCode(element);
        if (elementByCode == null) {
            modelAndView.addObject("msg", elementByCode + " в облести " + area + " не найдена");
            return modelAndView;
        }

        elementByCode.setState(areaByCode, Integer.valueOf(state));
        modelAndView.addObject("msg", state + " установлен объекту " + element);

        return modelAndView;
    }

    @RequestMapping("control/test/")
    public ModelAndView test() {

        ModelAndView modelAndView = new ModelAndView("control");
        test1();

        modelAndView.addObject("msg", "Тестовая установка отработала");

        return modelAndView;
    }

    private void test1() {
        Thread thread = new Thread(() -> {
            try {
                IArea areaByCode = polygon.getAreaByCode("SplitPoint.A");
                areaByCode.getElementsByType(Sv.class).forEach(iStationElement ->
                        {
                            try {
                                setState("SplitPoint.A", iStationElement.getElementCode(), SV_CLOSED, 0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                );

                IStationElement element = polygon.getElementByAreaAndCode("Stage.A.B", "У1Ч");

                setState("SplitPoint.A", "ПР1", PR_BUSY, 0);
                setState(element, UP_BUSY, 1); // "У1Ч"

                setState(element.getEven(), SV_OPEN, 0); // "НД"
                setState(element.getEven().getEven(), UP_BUSY, 1); // "УПН"

                setState(element, UP_NOT_BUSY, 0); // "У1Ч"
                setState(element.getEven().getEven().getEven(), ST_BUSY, 1); // "СТ1"

                element = element.getEven();

                setState(element, SV_CLOSED, 0); // "НД"
                setState(element.getEven(), UP_NOT_BUSY, 0); // "УПН"
                setState(element.getEven().getEven().getEven().getEven(), ST_MINUS, 0); // "СТ9"
                setState(element.getEven().getEven().getEven(), ST_BUSY, 1); // "СТ5"

                setState(element.getEven(), UP_NOT_BUSY, 0); // "УПН"
                setState(element.getEven().getEven(), ST_NOT_BUSY, 0); // "СТ1"
                setState("SplitPoint.A", "ПР1", PR_NOT_BUSY, 0); // "ПР1"
                setState(element.getEven().getEven().getEven().getEven().getEven(), SV_OPEN, 0); // "Ч3"
                setState(element.getEven().getEven().getEven().getEven(), ST_BUSY, 1); // "СТ9"

                element = element.getEven().getEven().getEven();
                setState(element, ST_NOT_BUSY, 0); // "СТ5"
                setState(element.getEven().getEven().getEven(), PT_BUSY, 1); // "3"

                element = element.getEven();
                setState(element, ST_NOT_BUSY, 0); // "СТ9"
                setState(element.getEven(), SV_CLOSED, 2); // "Ч3"

                element = element.getEven().getEven();
                setState(element.getEven().getEven(), ST_MINUS, 0); // "СТ14"
                setState(element.getEven(), SV_OPEN, 2); // "Н3"

                setState(element.getEven().getEven().getEven().getEven(), SV_OPEN, 0); // "1Ч"
                setState(element.getEven().getEven(), ST_BUSY, 1); // "СТ14"

                setState(element.getEven().getEven().getEven(), UP_BUSY, 0); // "УУ1Ч"
                setState(element, PT_NOT_BUSY, 0); // "3"
                setState(element.getEven(), SV_CLOSED, 1); // "Н3"

                element = element.getEven().getEven();
                setState(element, ST_NOT_BUSY, 0); // "СТ14"
                setState(element.getEven().getEven().getEven(), UP_BUSY, 1); // "У1Н"

                element = element.getEven();
                setState(element, UP_NOT_BUSY, 0); // "УУ1Ч"
                setState(element.getEven(), SV_CLOSED, 0); // "1Ч"
                setState(element.getEven().getEven(), UP_BUSY, 1); // "У1Н"

                setState(element.getEven().getEven(), UP_NOT_BUSY, 1); // "У1Н"
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.run();
    }

    private void setState(String area, String element, int state, int sleep) throws InterruptedException {
        IArea areaByCode = polygon.getAreaByCode(area);
        IStationElement elementByCode = areaByCode.getElementByCode(element);
        elementByCode.setState(areaByCode, state);
        TimeUnit.SECONDS.sleep(sleep);
    }
    private void setState(IStationElement elementByCode, int state, int sleep) throws InterruptedException {
        elementByCode.setState(elementByCode.getAreas().get(0), state);
        TimeUnit.SECONDS.sleep(sleep);
    }
}
