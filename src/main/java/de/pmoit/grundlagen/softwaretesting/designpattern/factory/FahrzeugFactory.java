package de.pmoit.grundlagen.softwaretesting.designpattern.factory;

public abstract class FahrzeugFactory {
    public IFactoryFahrzeug create() {
        IFactoryFahrzeug fahrzeug = createFahrzeug();
        fahrzeug.build();// Hier könnten weitere Methoden aufgerufen werden
        return fahrzeug;
    }

    protected abstract IFactoryFahrzeug createFahrzeug();
}
