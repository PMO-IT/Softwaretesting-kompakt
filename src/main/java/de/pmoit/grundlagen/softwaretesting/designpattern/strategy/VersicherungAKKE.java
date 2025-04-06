package de.pmoit.grundlagen.softwaretesting.designpattern.strategy;

public class VersicherungAKKE implements IVersicherung {
    String tarif;
    int tarifpreis;

    @Override
    public String getTarifName(Auto auto) {
        System.out.println("Tarif Teilkasko");
        return tarif;
    }

    @Override
    public int getTarifPreis(Auto auto) {
        System.out.println("225€ pro Jahr");
        return tarifpreis;
    }

}
