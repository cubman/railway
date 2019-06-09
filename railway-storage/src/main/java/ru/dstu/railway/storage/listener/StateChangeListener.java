package ru.dstu.railway.storage.listener;


import ru.dstu.railway.api.area.IArea;
import ru.dstu.railway.api.element.IStationElement;
import ru.dstu.railway.api.listener.IStateListener;
import ru.dstu.railway.model.element.AbstractElement;
import ru.dstu.railway.model.polygon.RailwayPolygon;
import ru.dstu.railway.storage.dao.StatesDao;
import ru.dstu.railway.storage.entity.StatesEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StateChangeListener implements IStateListener {

    private RailwayPolygon railwayPolygon;
    private StatesDao statesDao;

    public StateChangeListener(RailwayPolygon railwayPolygon, StatesDao statesDao) {
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
        if (statesDao.count() == 0) {
            StatesEntity statesEntity = new StatesEntity();
            statesEntity.setCode("СТ11");
            statesEntity.setType("st");
            statesEntity.setState(1);
            statesEntity.setLastChanged(LocalDateTime.now());
            statesDao.save(statesEntity);
        }
        Iterable<StatesEntity> all = statesDao.findAll();

        System.out.println("УРА!!!!!!!!!!" + all.iterator().next().getCode());
    }
}
