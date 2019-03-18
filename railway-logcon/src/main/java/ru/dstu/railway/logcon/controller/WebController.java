package ru.dstu.railway.logcon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.polygon.IPolygon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WebController {

    @Autowired
    private IPolygon polygon;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("msg", "Начало");
        modelAndView.addObject("areas", polygon.getAreas());
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
