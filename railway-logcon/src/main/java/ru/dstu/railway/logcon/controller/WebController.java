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
import ru.dstu.railway.polygon.IPolygon;

import java.util.logging.Logger;

@Controller
public class WebController  {

    private static final Logger LOGGER = Logger.getLogger(WebController.class.getName());

    @Autowired
    private IPolygon polygon;
    @Autowired
    private IMessageHolder messageHolder;

    public WebController() {

    }

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

        return modelAndView;
    }


}
