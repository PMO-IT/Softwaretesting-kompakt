package de.pmoit.grundlagen.softwaretesting.designpattern.factory;

public class Main {
    public static void main(String[] args) {
        Auto fahrzeug = ( Auto ) new FactoryAuto().create();
        fahrzeug.test();
    }
}
