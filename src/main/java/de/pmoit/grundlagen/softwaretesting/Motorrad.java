package de.pmoit.grundlagen.softwaretesting;

public class Motorrad extends Auto {
    int baujahr;

    public Motorrad(String constructorFarbe, int constructorHoechstgeschwindigkeit, String constructorMarke,
        String constructorModel, int constructorBaujahr) {
        super(constructorFarbe, constructorHoechstgeschwindigkeit, constructorMarke, constructorModel);
        baujahr = constructorBaujahr;
    }

    @Override
    public String setzteNeueFarbe(String neueFarbe) {
        if (marke.equals("BMW")) {
            if (neueFarbe.equals("grün") || neueFarbe.equals("pink") || neueFarbe.equals("rot")) {
                farbe = neueFarbe;
            }
        }
        return farbe;
    }

    @Override
    public void printMarke() {
        super.printMarke();
        System.out.println("Hier steht noch Zusatzinformation!");
    }
}
