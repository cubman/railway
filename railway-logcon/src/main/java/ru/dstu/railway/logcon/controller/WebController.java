package ru.dstu.railway.logcon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dstu.railway.area.IArea;
import ru.dstu.railway.element.IStationElement;
import ru.dstu.railway.logcon.struct.JArea;
import ru.dstu.railway.logcon.struct.JElement;
import ru.dstu.railway.logcon.struct.JNextElement;
import ru.dstu.railway.logcon.struct.JOwnArea;
import ru.dstu.railway.message.IMessageHolder;
import ru.dstu.railway.message.MessageLevel;
import ru.dstu.railway.parse.polygon.struct.XmlPolygon;
import ru.dstu.railway.polygon.IPolygon;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class WebController  {

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

    @RequestMapping(name ="api/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<JArea> areaApi() {
        return getJsonPolygon();
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


    private List<JArea> getJsonPolygon() {
        List<JArea> areas = new ArrayList<>();

        for (IArea area : polygon.getAreas()) {
            List<JElement> elements = new ArrayList<>();
            for (IStationElement element : area.getElements().values()) {
                JElement jElement = new JElement();
                jElement.setCode(element.getElementCode());
                jElement.setType(element.getClass().getSimpleName());
                jElement.setOwnAreas(getAreasForElement(element));
                jElement.setState(element.getState());
                jElement.setEven(element.getEven() == null ? null :
                        new JNextElement(element.getEven().getElementCode(),
                                element.getEven().getClass().getSimpleName(),
                                getAreasForElement(element.getEven())));
                jElement.setOdd(element.getOdd() == null ? null :
                        new JNextElement(element.getOdd().getElementCode(),
                                element.getOdd().getClass().getSimpleName(),
                                getAreasForElement(element.getOdd())));
                elements.add(jElement);
            }
            areas.add(new JArea(area.getAreaCode(), area.getEsr(), elements));
        }

        return areas;
    }

    private List<JOwnArea> getAreasForElement(IStationElement element) {
        return element == null ? null :element.getAreas().stream()
                .map(iArea -> new JOwnArea(iArea.getAreaCode(), iArea.getEsr()))
                .collect(Collectors.toList());
    }

}
