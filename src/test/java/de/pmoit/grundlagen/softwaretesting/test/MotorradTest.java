package de.pmoit.grundlagen.softwaretesting.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.pmoit.grundlagen.softwaretesting.Motorrad;


public class MotorradTest {

    private Motorrad motorradBMWRotF900;

    @BeforeEach
    void createAutoObjekt() {
        motorradBMWRotF900 = new Motorrad("rot", 350, "BMW", "F 900 GS", 1990);
    }

    @Test
    public void lege_rotesBMWF900GSan_Farbe_rot_erfolgreich_an() {
        assertEquals("rot", motorradBMWRotF900.getFarbe());
    }

    @Test
    public void lege_rotesBMWF900GSan_Hoechstgeschwindigkeit_350_erfolgreich_an() {
        assertEquals(350, motorradBMWRotF900.getHoechstgeschwindigkeit());
    }

    // ...

    @Test
    public void lege_rotesBMWF900GSan_Marke_BMW_erfolgreich_an() {
        assertEquals("BMW", motorradBMWRotF900.getMarke());
    }

}
