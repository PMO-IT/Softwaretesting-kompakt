package de.pmoit.grundlagen.softwaretesting.designpattern.strategy;

public class Auto {
    String farbe = "blau";
    int hoechstgeschwindigkeit = 200;
    String marke = "Tesla";
    String model = "X";

    // Konstructor mit Parametern
    public Auto(String constructorFarbe, int constructorHoechstgeschwindigkeit, String constructorMarke,
        String constructorModel) {
        farbe = constructorFarbe;
        hoechstgeschwindigkeit = constructorHoechstgeschwindigkeit;
        marke = constructorMarke;
        model = constructorModel;
    }

    public int getHoechstgeschwindigkeit() {
        return hoechstgeschwindigkeit;
    }

    public String getMarke() {
        return marke;
    }

    public String getFarbe() {
        return farbe;
    }

    // Druck die aktuelle Höchstgeschwinditkeit auf der Konsole
    public void printAktuelleHoechstssGeschwindigkeit() {
        System.out.println(hoechstgeschwindigkeit);
    }

    public void printFarbe() {
        System.out.println(farbe);
    }

    public void printMarke() {
        System.out.println(marke);
    }

    public void printModel() {
        System.out.println(model);
    }

}
