package ru.dstu.railway.storage.listener;


import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.listener.IStateListener;
import ru.dstu.railway.model.element.AbstractElement;
import ru.dstu.railway.model.polygon.RailwayPolygon;
import ru.dstu.railway.storage.dao.StatesDao;
import ru.dstu.railway.storage.entity.StatesEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DbStateListener implements IStateListener {

    private RailwayPolygon railwayPolygon;
    private StatesDao statesDao;

    public DbStateListener(RailwayPolygon railwayPolygon, StatesDao statesDao) {
        this.railwayPolygon = railwayPolygon;
        this.statesDao = statesDao;
    }

    public void subscribeToStationElements() {
        railwayPolygon.getAreas().forEach(area -> area.getElements().values().forEach(
                element ->
                        ((AbstractElement)element).addStateListener(this)));
    }

    @Override
    public void notify(IArea area, IStationElement element) {
        Iterable<StatesEntity> codeAndType = statesDao.findByCodeAndType(element.getElementCode(), element.getClass().getName());
        StatesEntity statesEntity;

        if (codeAndType.iterator().hasNext()) {
            statesEntity = codeAndType.iterator().next();
        } else {
            statesEntity = new StatesEntity();
            statesEntity.setCode(element.getElementCode());
            statesEntity.setType(element.getClass().getName());
        }
        statesEntity.setArea(area.getAreaCode());
        statesEntity.setState(element.getState().getState());
        statesEntity.setLastChanged(LocalDateTime.ofInstant( element.getState().getLastChange().toInstant(), ZoneId.systemDefault()));

        statesDao.save(statesEntity);
    }
}
