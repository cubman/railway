package ru.dstu.railway.paint.draw;

import ru.dstu.railway.element.*;
import ru.dstu.railway.paint.IDrawElement;
import ru.dstu.railway.paint.draw.drawer.*;

public class DrawFactory {

    public IDrawElement getDrawer(IStationElement stationElement, int templateVersion) {
        IDrawElement drawElement = null;

        if (stationElement instanceof Kp) {
            if (templateVersion == 1) {
                drawElement = new DrawKp(stationElement);
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
            drawElement = new DrawLs();
        }

        return drawElement;
    }
}
