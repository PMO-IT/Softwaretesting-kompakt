package de.pmoit.grundlagen.softwaretesting.test.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import de.pmoit.grundlagen.softwaretesting.test.selenium.pageobjects.FahrzeugPage;


public class FahrzeugAnlegenMobileTest {
    private static FirefoxDriver driver; // Alternativ: ChromeDriver

    @BeforeAll
    static void startBrowser() {
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions opt = new FirefoxOptions();
        profile.setPreference("general.useragent.override",
            "Mozilla/5.0 (iPhone14,3; U; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/19A346 Safari/602.1");
        opt.setProfile(profile);
        opt.addArguments("--width=2778");
        opt.addArguments("--height=1284");
        driver = new FirefoxDriver(opt);
        driver.get("http://localhost:8080/softwaretesting/");
    }

    @Test
    public void Motorrad_anlegen_iphone13pro_max_erfolgreich() {
        FahrzeugPage fahrzeugPage = new FahrzeugPage(driver);
        fahrzeugPage.selectFahrzeugart("motorrad").setMarke("VW").setModel("Tiguan").setFarbe("Rot")
            .setHoechstgeschwindigkeit("200").setKennzeichen("TST-PM-1337").setBaujahr("1990").clickSubmitButton();
        String successMsgText = fahrzeugPage.getSuccessMsg();
        assertEquals("Fahrzeug erfolgreich angelegt!", successMsgText);
    }
}
