package ru.dstu.station.element;

public interface IStationElement {
    String getCode();

    IStationElement getEven();

    IStationElement getOdd();

    void setState(int state);
}
