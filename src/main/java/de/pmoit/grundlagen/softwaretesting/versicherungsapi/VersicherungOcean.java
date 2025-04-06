package de.pmoit.grundlagen.softwaretesting.versicherungsapi;

import de.pmoit.grundlagen.softwaretesting.Auto;

public class VersicherungOcean implements IVersicherung {
    String tarif;
    int tarifpreis;
    final String APIKey;

    public VersicherungOcean(String APIKey) {
        this.APIKey = APIKey;
    }

    @Override
    public String getTarifName(Auto auto) {
        // Verschiedene Logik zur Abfrage des Tarifs von der Ocean Schnittstelle
        return tarif;
    }

    @Override
    public int getTarifPreis(Auto auto) {
        // Verschiedene Logik zur Abfrage des Tarifs von der Ocean Schnittstelle
        return tarifpreis;
    }
}
