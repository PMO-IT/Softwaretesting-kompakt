package de.pmoit.grundlagen.softwaretesting.designpattern.strategy;

public interface IVersicherung {
    String getTarifName(Auto auto);

    int getTarifPreis(Auto auto);
}
