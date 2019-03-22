package ru.dstu.railway.logcon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.message.IMessageHolder;
import ru.dstu.railway.message.MessageLevel;
import ru.dstu.railway.paint.IPaintPolygon;
import ru.dstu.railway.polygon.IPolygon;

import java.util.logging.Logger;

import static ru.dstu.railway.constant.Constant.*;

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
        setState("SplitPoint.A", "СТ7", ST_PLUS);
        setState("SplitPoint.A", "СТ9", ST_MINUS);
        setState("SplitPoint.A", "СТ9", ST_BUSY);
        setState("SplitPoint.A", "СТ14", ST_NON);
        setState("SplitPoint.A", "2", KP_NON);
        setState("SplitPoint.A", "4", KP_NOT_BUSY);
        setState("SplitPoint.A", "6", KP_BUSY);
        setState("SplitPoint.A", "Н2", SV_OPEN);
        setState("SplitPoint.A", "Н4", SV_CLOSED);
        setState("SplitPoint.A", "Н6", SV_NON);
        setState("SplitPoint.A", "УП2Ч", UP_NON);
        setState("SplitPoint.A", "УУ1Ч", UP_NOT_BUSY);
        setState("Stage.A.C", "У1Н", UP_BUSY);

        modelAndView.addObject("msg", "Тестовая установка отработала");

        return modelAndView;
    }

    private void setState(String area, String element, int state) {
        IArea areaByCode = polygon.getAreaByCode(area);
        IStationElement elementByCode = areaByCode.getElementByCode(element);
        elementByCode.setState(state);
        paintPolygon.setColors(areaByCode, elementByCode);
    }
}
