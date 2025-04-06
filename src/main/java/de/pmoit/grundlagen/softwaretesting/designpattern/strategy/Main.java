package de.pmoit.grundlagen.softwaretesting.designpattern.strategy;

public class Main {
    public static void main(String[] args) {

        Auto autoBlauerVWTiguan = new Auto("blau", 120, "VW", "Tiguan");

        // AKKE Versicherung
        VersicherungStrategy versicherungStrategyAkke = new VersicherungStrategy(new VersicherungAKKE());
        versicherungStrategyAkke.getTarifName(autoBlauerVWTiguan);
        versicherungStrategyAkke.getTarifPreis(autoBlauerVWTiguan);

        // Ocean Versicherung
        VersicherungStrategy versicherungStrategyOcean = new VersicherungStrategy(new VersicherungOcean());
        versicherungStrategyOcean.getTarifName(autoBlauerVWTiguan);
        versicherungStrategyOcean.getTarifPreis(autoBlauerVWTiguan);
    }
}
