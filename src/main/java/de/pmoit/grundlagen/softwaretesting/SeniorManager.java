package de.pmoit.grundlagen.softwaretesting;

public class SeniorManager extends Mitarbeiter {

    int stufe;
    double gehalt;
    int jahreImUnternehmen;

    public SeniorManager(String constructorVorname, String constructorNachname, int constructorStufe,
        int constructorJahreImUnternehmen) {
        super(constructorVorname, constructorNachname);
        stufe = constructorStufe;
        jahreImUnternehmen = constructorJahreImUnternehmen;
    }

    @Override
    public void berechneGehalt() {
        gehalt = stufe * 2500 * ( jahreImUnternehmen * 0.5 );
    }

}
