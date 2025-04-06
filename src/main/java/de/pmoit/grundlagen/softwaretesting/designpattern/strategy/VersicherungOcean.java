package de.pmoit.grundlagen.softwaretesting.designpattern.strategy;

public class VersicherungOcean implements IVersicherung {
    String tarif;
    int tarifpreis;

    @Override
    public String getTarifName(Auto auto) {
        System.out.println("Tarif Vollkasko");
        return tarif;
    }

    @Override
    public int getTarifPreis(Auto auto) {
        System.out.println("350€ pro Jahr");
        return tarifpreis;
    }
}
