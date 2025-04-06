package de.pmoit.grundlagen.softwaretesting.designpattern.factory;

public class FactoryAuto extends FahrzeugFactory {
    @Override
    protected Auto createFahrzeug() {
        return new Auto();
    }
}
