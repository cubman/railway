package ru.dstu.railway.paint.draw;

import ru.dstu.railway.api.element.*;
import ru.dstu.railway.api.paint.IDrawElement;
import ru.dstu.railway.model.element.*;
import ru.dstu.railway.paint.draw.drawer.*;

public class DrawFactory {

    public IDrawElement getDrawer(IStationElement stationElement, int templateVersion) {
        IDrawElement drawElement = null;

        if (stationElement instanceof Pt) {
            if (templateVersion == 1) {
                drawElement = new DrawPt(stationElement);
            }
        } else if (stationElement instanceof Up) {
            if (templateVersion == 1) {
                drawElement = new DrawUp(stationElement);
            }
        } else if (stationElement instanceof Sv) {
            if (templateVersion >= 1 && templateVersion <= 2) {
                drawElement = new DrawSv(stationElement);
            }
        } else if (stationElement instanceof St) {
            if (templateVersion >= 1 && templateVersion <= 6) {
                drawElement = new DrawSt(stationElement);
            }
        } else if (stationElement instanceof Ls) {
            if (templateVersion == 1) {
                drawElement = new DrawLs();
            }
        } else if (stationElement instanceof Pr) {
            if (templateVersion == 1) {
                drawElement = new DrawPr(stationElement);
            }
        } else if (stationElement instanceof Mr) {
            if (templateVersion == 1) {
                drawElement = new DrawMr(stationElement);
            }
        }

        return drawElement;
    }
}
