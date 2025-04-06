package de.pmoit.grundlagen.softwaretesting;

import de.pmoit.grundlagen.softwaretesting.exceptions.AutoGeschwindigkeitsException;


public class Auto {
    String farbe = "blau";
    int hoechstgeschwindigkeit = 200;
    String marke = "Tesla";
    String model = "X";
    String kennzeichen = "";

    // Standardkonstructor
    public Auto() {
        // Keine Paramters
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    // Konstructor mit Parametern
    public Auto(String constructorFarbe, int constructorHoechstgeschwindigkeit, String constructorMarke,
        String constructorModel) {
        farbe = constructorFarbe;
        hoechstgeschwindigkeit = constructorHoechstgeschwindigkeit;
        marke = constructorMarke;
        model = constructorModel;
    }

    public String setzteNeueFarbe(String neueFarbe) {
        if (marke.equals("Tesla")) {
            if (neueFarbe.equals("blau") || neueFarbe.equals("rot") || neueFarbe.equals("schwarz") || neueFarbe.equals(
                "weiss")) {
                farbe = neueFarbe;
            }
        } else if ( ( marke.equals("VW") && neueFarbe.equals("schwarz") ) || ( marke.equals("VW") && neueFarbe.equals(
            "weiss") )) {
            farbe = neueFarbe;
        } else if ( ! marke.equals("VW") && ! marke.equals("Tesla")) {
            farbe = neueFarbe;
        }
        return farbe;
    }

    public int setzeHoechstgeschwindigkeit(int neueHoechstgeschwindigkeit) throws AutoGeschwindigkeitsException {
        if (neueHoechstgeschwindigkeit > 0) {
            hoechstgeschwindigkeit = neueHoechstgeschwindigkeit;
        } else if (neueHoechstgeschwindigkeit == 0) {
            throw new AutoGeschwindigkeitsException("Der neue Höchstwert ist 0");
        } else {
            throw new AutoGeschwindigkeitsException("Der neue Höchstwert ist kleiner 0");
        }
        return hoechstgeschwindigkeit;
    }

    public int getHoechstgeschwindigkeit() {
        return hoechstgeschwindigkeit;
    }

    public String getMarke() {
        return marke;
    }

    public String getModel() {
        return model;
    }

    public String getFarbe() {
        return farbe;
    }

    // Druck die aktuelle Höchstgeschwindigkeit auf der Konsole
    public void printAktuelleHoechstGeschwindigkeit() {
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
