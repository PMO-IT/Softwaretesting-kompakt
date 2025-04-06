package de.pmoit.grundlagen.softwaretesting.test.selenium;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.firefox.FirefoxDriver;


public class CookieTest {
    private static FirefoxDriver driver;

    @BeforeAll
    static void startBrowser() {
        driver = new FirefoxDriver(); // Alternativ: new ChromeDriver();
        driver.get("https://www.google.com/");
    }

    @AfterAll
    static void closeBrowser() {
        driver.close();
    }

    @Test
    public void Cookie_test() {
        String cookieName = "SOCS";
        driver.findElement(By.id("L2AGLb")).click();
        driver.navigate().refresh();
        Cookie googleCookie = driver.manage().getCookieNamed(cookieName);
        assertNotNull(googleCookie);

        driver.manage().deleteCookieNamed(cookieName);
        Cookie deletedGoogleCookie = driver.manage().getCookieNamed(cookieName);
        assertNull(deletedGoogleCookie);
    }

}
