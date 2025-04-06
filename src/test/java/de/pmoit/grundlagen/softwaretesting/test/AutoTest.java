package de.pmoit.grundlagen.softwaretesting.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import de.pmoit.grundlagen.softwaretesting.Auto;
import de.pmoit.grundlagen.softwaretesting.exceptions.AutoGeschwindigkeitsException;


public class AutoTest {
    private Auto autoBlauerVWTiguan;

    @BeforeEach
    void createAutoObjekt() {
        autoBlauerVWTiguan = new Auto("blau", 120, "VW", "Tiguan");
    }

    @Test
    public void aendere_die_Hoechstgeschwindigkeit_erfolgreich() throws IOException, AutoGeschwindigkeitsException {

        autoBlauerVWTiguan.setzeHoechstgeschwindigkeit(225);
        assertEquals(autoBlauerVWTiguan.getHoechstgeschwindigkeit(), 225);
    }

    @Test
    public void aendere_die_Hoechstgeschwindigkeit_mit_Wert_ist_kleiner_0_throw_Exception() throws IOException,
        AutoGeschwindigkeitsException {

        Exception exception = assertThrows(AutoGeschwindigkeitsException.class, () -> autoBlauerVWTiguan
            .setzeHoechstgeschwindigkeit( - 1));
        assertEquals("Der neue Höchstwert ist kleiner 0", exception.getMessage());
    }

    @Test
    public void aendere_die_Hoechstgeschwindigkeit_mit_Wert_ist_0_throw_Exception() throws IOException,
        AutoGeschwindigkeitsException {

        Exception exception = assertThrows(AutoGeschwindigkeitsException.class, () -> autoBlauerVWTiguan
            .setzeHoechstgeschwindigkeit(0));
        assertEquals("Der neue Höchstwert ist 0", exception.getMessage());
    }

    @ParameterizedTest
    @ArgumentsSource(AutoTestObjects.class)
    public void aendere_die_farbe_erfolgreich(Auto autoObject, String neueFarbe) {
        autoObject.setzteNeueFarbe(neueFarbe);
        assertEquals(autoObject.getFarbe(), neueFarbe);
    }

    static class AutoTestObjects implements ArgumentsProvider {
        @Override
        public Stream< ? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(new Auto("blau", 120, "VW", "Tiguan"), "weiss"), Arguments.of(new Auto("blau", 120,
                "Tesla", "3"), "rot"), Arguments.of(new Auto("lila", 120, "Tesla", "X"), "blau"));
        }
    }
}
