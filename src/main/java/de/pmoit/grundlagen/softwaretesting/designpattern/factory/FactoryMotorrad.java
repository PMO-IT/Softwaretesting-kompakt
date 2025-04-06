package de.pmoit.grundlagen.softwaretesting.designpattern.factory;

public class FactoryMotorrad extends FahrzeugFactory {
    @Override
    protected Motorrad createFahrzeug() {
        return new Motorrad();
    }
}
