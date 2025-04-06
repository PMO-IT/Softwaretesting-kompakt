package de.pmoit.grundlagen.softwaretesting;

public class Mitarbeiter {

    String vorname;
    String nachname;
    double gehalt;

    public Mitarbeiter(String constructorVorname, String constructorNachname) {
        vorname = constructorVorname;
        nachname = constructorNachname;
    }

    public void berechneGehalt() {
        gehalt = 2500;
    }

    public double getAktuellesGehalt() {
        return gehalt;
    }
}
