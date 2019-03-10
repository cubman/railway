package ru.sdtu.railway.parse.logcon.struct;

import ru.dstu.railway.parse.exception.ParseException;

public class XmlElse extends AbstractXmlFunction {

    @Override
    public void setXmlElse(XmlElse xmlElse) {
        throw new ParseException("Переход из else в else невозможен");
    }
}
