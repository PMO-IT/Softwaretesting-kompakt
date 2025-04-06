package de.pmoit.grundlagen.softwaretesting.designpattern.strategy;

public class Motorrad extends Auto {
    int baujahr;

    public Motorrad(String constructorFarbe, int constructorHoechstgeschwindigkeit, String constructorMarke,
        String constructorModel, int constructorBaujahr) {
        super(constructorFarbe, constructorHoechstgeschwindigkeit, constructorMarke, constructorModel);
        baujahr = constructorBaujahr;
    }

    @Override
    public void printMarke() {
        super.printMarke();
        System.out.println("Hier steht noch Zusatzinformation!");
    }
}
