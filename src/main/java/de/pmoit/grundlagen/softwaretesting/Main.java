package de.pmoit.grundlagen.softwaretesting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import de.pmoit.grundlagen.softwaretesting.datenbank.MySQLController;
import de.pmoit.grundlagen.softwaretesting.versicherungsapi.IVersicherung;
import de.pmoit.grundlagen.softwaretesting.versicherungsapi.VersicherungAKKE;
import de.pmoit.grundlagen.softwaretesting.versicherungsapi.VersicherungOcean;


public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws SQLException {
        // Objekte Auto
        Auto f1 = new Auto("blau", 270, "VW", "A8");
        Auto f2 = new Auto("schwarz", 270, "BMW", "X3");
        Auto f3 = new Auto("gelb", 310, "Porsche", "Panamera");

        // HashMap
        createHashmap(f1, f2, f3);

        // ArrayList
        createArrayList(f1, f2, f3);

        createArrayAlternativ(f1, f2, f3);

        // Mehrdimensionales Array
        createMehrdimensionalesArray(f1, f2, f3);

        // IVersicherung versicherung = new VersicherungAKKE();
        // String tarif = versicherung.getTarifName(f1);

        Auto a = new Auto("blau", 270, "VW", "A8");
        // a.printMarke();

        Motorrad motorradBMWRotF900 = new Motorrad("rot", 350, "BMW", "F 900 GS", 1990);
        // motorradBMWRotF900.printMarke();

        // // Ruft StandardKonstruktor auf
        // Auto autoInstanz = new Auto();
        // autoInstanz.printAktuelleHoechstGeschwindigkeit();
        //
        // // Ruft Konstruktor mit Parametern auf
        // Auto autoInstanz2 = new Auto("Blau", 250, "Tesla", "3");
        // autoInstanz2.printAktuelleHoechstGeschwindigkeit();

        String input;
        HashMap<String, Auto> autoMap = new HashMap<String, Auto>();

        // Datenbank wird hier angebunden
        MySQLController dbController = new MySQLController("user", "password", "localhost", 3306, "fahrzeugverwaltung");

        do {
            System.out.println(
                "Bitte Menü auswählen: auto / motorrad / versicherung / anzeigen / speichern / laden / x zum beenden");
            input = readConsoleIn();

            switch (input) {
                case "auto":
                    guiAutoMenue(autoMap);
                    break;
                case "motorrad":
                    guiMotorradMenue(autoMap);
                    break;
                case "versicherung":
                    guiVersicherungMenue(autoMap);
                    break;
                case "anzeigen":
                    for (Auto auto : autoMap.values()) {
                        System.out.println(auto.model + " " + auto.marke + " " + auto.farbe);
                    }
                    break;
                case "speichern":
                    guiSpeichernMenue(autoMap, dbController);
                    break;
                case "laden":
                    autoMap = guiLadeMethode(dbController, autoMap);
                    break;
                default:
                    break;
            }
        } while ( ! input.equals("x"));
    }

    private static void guiSpeichernMenue(HashMap<String, Auto> autoMap, MySQLController dbController) throws SQLException {
        for (String autoMapId : autoMap.keySet()) {
            String className = autoMap.get(autoMapId).getClass().getName();
            if (className.contains("Auto")) {
                Auto auto = autoMap.get(autoMapId);
                dbController.executeSql("insert into fahrzeug('id','model','geschwindigkeit','marke','farbe') " + "values("
                    + autoMapId + "," + auto.model + "," + auto.hoechstgeschwindigkeit + "," + auto.marke + "," + auto.farbe
                    + ")");
            } else if (className.contains("Motorrad")) {
                Motorrad motorrad = ( Motorrad ) autoMap.get(autoMapId);
                dbController.executeSql("insert into fahrzeug('id','model','geschwindigkeit','marke','farbe','baujahr')"
                    + "values(" + autoMapId + "," + motorrad.model + "," + motorrad.hoechstgeschwindigkeit + ","
                    + motorrad.marke + "," + motorrad.farbe + "," + motorrad.baujahr + ")");
            }

        }
    }

    private static void guiVersicherungMenue(HashMap<String, Auto> autoMap) {
        System.out.println("Versicherungsdaten für Fahrzeuge anzeigen lassen");
        Iterator<Entry<String, Auto>> autoMapIterator = autoMap.entrySet().iterator();

        while (autoMapIterator.hasNext()) {
            Entry<String, Auto> pair = autoMapIterator.next();
            IVersicherung versicherung = null;
            if (pair.getValue().marke.equals("VW")) {
                versicherung = new VersicherungOcean("some api key");
            } else {
                versicherung = new VersicherungAKKE("some api key");
            }
            System.out.println(versicherung.getTarifName(pair.getValue()));
            autoMapIterator.remove();
        }
    }

    private static void guiMotorradMenue(HashMap<String, Auto> autoMap) {
        System.out.println("Bitte ID, Autofarbe, Geschwindigkeit, Marke, Model, Baujahr eingeben");
        String id = readConsoleIn();
        String farbe = readConsoleIn();

        int geschwindigkeit = 250;
        try {
            geschwindigkeit = readConsoleInAsInt();
        } catch (NumberFormatException e) {
            System.out.println("Bitte nur Zahlen eingeben!");
        }

        String marke = readConsoleIn();
        String model = readConsoleIn();
        int baujahr = readConsoleInAsInt();

        Motorrad motorradInstanz = new Motorrad(farbe, geschwindigkeit, marke, model, baujahr);
        autoMap.put(id, motorradInstanz);
    }

    private static void guiAutoMenue(HashMap<String, Auto> autoMap) {
        System.out.println("Bitte ID, Autofarbe, Geschwindigkeit, Marke und Model eingeben");
        String id = readConsoleIn();
        String farbe = readConsoleIn();

        int geschwindigkeit = 250;
        try {
            geschwindigkeit = readConsoleInAsInt();
        } catch (NumberFormatException e) {
            System.out.println("Bitte nur Zahlen eingeben!");
        }

        String marke = readConsoleIn();
        String model = readConsoleIn();

        Auto autoInstanz = new Auto(farbe, geschwindigkeit, marke, model);
        autoMap.put(id, autoInstanz);
    }

    private static HashMap<String, Auto> guiLadeMethode(MySQLController dbController, HashMap<String, Auto> autoMap)
        throws SQLException {
        ResultSet resultSet = dbController.select("select * from fahrzeug");
        while (resultSet.next()) {
            String autoMapId = resultSet.getString("id");
            String model = resultSet.getString("model");
            String farbe = resultSet.getString("farbe");
            int geschwindigkeit = resultSet.getInt("geschwindigkeit");
            String marke = resultSet.getString("marke");
            int baujahr = resultSet.getInt("baujahr");

            Auto fahrzeug = null;
            if (baujahr > 0) {
                fahrzeug = new Motorrad(farbe, geschwindigkeit, marke, model, baujahr);
            } else if (baujahr == 0) {
                fahrzeug = new Auto(farbe, geschwindigkeit, marke, model);
            } else {
                // Handle Exception
            }
            autoMap.put(autoMapId, fahrzeug);
        }
        return autoMap;
    }

    @SuppressWarnings("unused")
    private static void createArrayAlternativ(Auto f1, Auto f2, Auto f3) {
        Auto[] fahrzeuge = { f1, f2, f3 };
    }

    private static void createMehrdimensionalesArray(Auto f1, Auto f2, Auto f3) {
        Auto autos[][] = new Auto[3][3];
        autos[0][0] = f1;
        autos[0][1] = f2;
        autos[1][0] = f3;
    }

    private static void createArrayList(Auto f1, Auto f2, Auto f3) {
        List<Auto> autoList = new ArrayList<Auto>();
        autoList.add(f1);
        autoList.add(1, f2);
        autoList.add(f3);
    }

    @SuppressWarnings("unused")
    private static void createHashmap(Auto f1, Auto f2, Auto f3) {
        // HashMap
        HashMap<String, Auto> autoMap = new HashMap<String, Auto>();
        autoMap.put("VW1", f1);
        autoMap.put("BMW1", f2);
        autoMap.put("VW2", f3);

        Collection<Auto> autoResult = autoMap.values();
    }

    // Liest Eingabe als String
    @SuppressWarnings("resource")
    private static String readConsoleIn() {
        return new StringBuffer(new Scanner(System.in).nextLine()).toString();
    }

    // Liest Eingaben als int
    @SuppressWarnings("resource")
    private static int readConsoleInAsInt() {
        return Integer.parseInt(new StringBuffer(new Scanner(System.in).nextLine()).toString());
    }
}
