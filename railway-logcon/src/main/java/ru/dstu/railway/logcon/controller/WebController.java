package ru.dstu.railway.logcon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.base.message.IMessageHolder;
import ru.dstu.railway.api.base.message.MessageLevel;
import ru.dstu.railway.api.paint.IPaintPolygon;
import ru.dstu.railway.api.polygon.IPolygon;
import ru.dstu.railway.model.element.Sv;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static ru.dstu.railway.api.constant.Constant.*;

@Controller
public class WebController  {

    private static final Logger LOGGER = Logger.getLogger(WebController.class.getName());

    @Autowired
    private IPolygon polygon;
    @Autowired
    private IMessageHolder messageHolder;
    @Autowired
    private IPaintPolygon paintPolygon;

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

        elementByCode.setState(Integer.valueOf(state));
        modelAndView.addObject("msg", state + " установлен объекту " + element);

        paintPolygon.setColors(areaByCode, elementByCode);

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

                setState("SplitPoint.A", "ПР1", PR_BUSY, 0);
                setState("Stage.A.B", "У1Ч", UP_BUSY, 1);

                setState("SplitPoint.A", "НД", SV_OPEN, 0);
                setState("SplitPoint.A", "УПН", UP_BUSY, 1);

                setState("Stage.A.B", "У1Ч", UP_NOT_BUSY, 0);
                setState("SplitPoint.A", "СТ1", ST_BUSY, 1);

                setState("SplitPoint.A", "УПН", UP_NOT_BUSY, 0);
                setState("SplitPoint.A", "НД", SV_CLOSED, 0);
                setState("SplitPoint.A", "СТ9", ST_MINUS, 0);
                setState("SplitPoint.A", "СТ5", ST_BUSY, 1);

                setState("SplitPoint.A", "УПН", UP_NOT_BUSY, 0);
                setState("SplitPoint.A", "СТ1", ST_NOT_BUSY, 0);
                setState("SplitPoint.A", "ПР1", PR_NOT_BUSY, 0);
                setState("SplitPoint.A", "Ч3", SV_OPEN, 0);
                setState("SplitPoint.A", "СТ9", ST_BUSY, 1);


                setState("SplitPoint.A", "СТ5", ST_NOT_BUSY, 0);
                setState("SplitPoint.A", "3", PT_BUSY, 1);

                setState("SplitPoint.A", "СТ9", ST_NOT_BUSY, 0);
                setState("SplitPoint.A", "Ч3", SV_CLOSED, 1);

//                setState("SplitPoint.A", "2", PT_NON);
//                setState("SplitPoint.A", "4", PT_NOT_BUSY);
//                setState("SplitPoint.A", "6", PT_BUSY);
//                setState("SplitPoint.A", "Н2", SV_OPEN);
//                setState("SplitPoint.A", "Н4", SV_CLOSED);
//                setState("SplitPoint.A", "Н6", SV_NON);
//                setState("SplitPoint.A", "УП2Ч", UP_NON);
//                setState("SplitPoint.A", "УУ1Ч", UP_NOT_BUSY);
//                setState("Stage.A.C", "У1Н", UP_BUSY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.run();
    }

    private void setState(String area, String element, int state, int sleep) throws InterruptedException {
        IArea areaByCode = polygon.getAreaByCode(area);
        IStationElement elementByCode = areaByCode.getElementByCode(element);
        elementByCode.setState(state);
        paintPolygon.setColors(areaByCode, elementByCode);
        TimeUnit.SECONDS.sleep(sleep);
    }
}
