package de.pmoit.grundlagen.softwaretesting.designpattern.strategy;

public class VersicherungStrategy {
    private IVersicherung versicherung;

    public VersicherungStrategy(IVersicherung versicherung) {
        this.versicherung = versicherung;
    }

    public String getTarifName(Auto fahrzeug) {
        return versicherung.getTarifName(fahrzeug);
    }

    public int getTarifPreis(Auto fahrzeug) {
        return versicherung.getTarifPreis(fahrzeug);
    }
}
