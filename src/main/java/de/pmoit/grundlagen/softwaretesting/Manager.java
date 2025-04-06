package de.pmoit.grundlagen.softwaretesting;

public class Manager extends Mitarbeiter {

    int stufe;

    public Manager(String constructorVorname, String constructorNachname, int constructorStufe) {
        super(constructorVorname, constructorNachname);
        stufe = constructorStufe;
    }

    @Override
    public void berechneGehalt() {
        gehalt = stufe * 5000;
    }

}
