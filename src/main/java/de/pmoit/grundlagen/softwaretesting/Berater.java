package de.pmoit.grundlagen.softwaretesting;

public class Berater extends Mitarbeiter {

    public Berater(String constructorVorname, String constructorNachname) {
        super(constructorVorname, constructorNachname);
    }

    @Override
    public void berechneGehalt() {
        gehalt = 3000;
    }
}
