package de.pmoit.grundlagen.softwaretesting.designpattern.factory;

public class Auto implements IFactoryFahrzeug {

    @Override
    public void build() {
        System.out.println("Hier wird ein Auto gebaut.");
    }

    public void test() {
        System.out.println("Das ist ein Test");
    }

}
