package de.pmoit.grundlagen.softwaretesting;

public class MitarbeiterZuFahrzeugZuweisung {

    Auto auto;
    Mitarbeiter mitarbeiter;

    public MitarbeiterZuFahrzeugZuweisung(Auto constructorAuto, Mitarbeiter constructorMitarbeiter) {
        auto = constructorAuto;
        auto.printMarke();
        mitarbeiter = constructorMitarbeiter;
    }

    public static void main(String[] args) {
        MitarbeiterZuFahrzeugZuweisung n = new MitarbeiterZuFahrzeugZuweisung(new Motorrad("blau", 250, "BMW", "I3", 1980),
            new Manager("Max", "Muster", 3));
    }

}
