package de.pmoit.grundlagen.softwaretesting.designpattern.factory;

public class Motorrad implements IFactoryFahrzeug {

    @Override
    public void build() {
        System.out.println("Hier wird ein Motorrad gebaut.");
    }

}
