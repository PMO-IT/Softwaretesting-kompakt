package de.pmoit.grundlagen.softwaretesting.versicherungsapi;

import de.pmoit.grundlagen.softwaretesting.Auto;

public interface IVersicherung {
    String getTarifName(Auto auto);

    int getTarifPreis(Auto auto);
}
