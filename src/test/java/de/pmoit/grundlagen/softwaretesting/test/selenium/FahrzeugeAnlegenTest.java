package de.pmoit.grundlagen.softwaretesting.test.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import de.pmoit.grundlagen.softwaretesting.test.selenium.pageobjects.FahrzeugPage;


public class FahrzeugeAnlegenTest {

    private static FirefoxDriver driver; // Alternativ: ChromeDriver

    @BeforeAll
    static void startBrowser() {
        driver = new FirefoxDriver(); // Alternativ: new ChromeDriver();
        driver.get("http://localhost:8080/softwaretesting/");
    }

    @AfterAll
    static void closeBrowser() {
        driver.close();
    }

    @Test
    public void Auto_anlegen_test_erfolgreich() {
        WebElement marke = driver.findElement(By.id("marke"));
        WebElement model = driver.findElement(By.id("model"));
        WebElement farbe = driver.findElement(By.id("farbe"));
        WebElement hoechstgeschwindigkeit = driver.findElement(By.id("hoechstgeschwindigkeit"));
        WebElement kennzeichen = driver.findElement(By.id("kennzeichen"));
        WebElement submitButton = driver.findElement(By.name("submit"));

        marke.sendKeys("VW");
        model.sendKeys("Tiguan");
        farbe.sendKeys("Rot");
        hoechstgeschwindigkeit.sendKeys("200");
        kennzeichen.sendKeys("TST-PM-1337");
        submitButton.click();

        WebElement successMsg = driver.findElement(By.id("success"));
        String successMsgText = successMsg.getText();
        assertEquals("Fahrzeug erfolgreich angelegt!", successMsgText);
    }

    @Test
    public void Motorrad_anlegen_test_erfolgreich() {
        WebElement marke = driver.findElement(By.id("marke"));
        WebElement model = driver.findElement(By.id("model"));
        WebElement farbe = driver.findElement(By.id("farbe"));
        WebElement hoechstgeschwindigkeit = driver.findElement(By.id("hoechstgeschwindigkeit"));
        WebElement kennzeichen = driver.findElement(By.id("kennzeichen"));
        WebElement baujahr = driver.findElement(By.id("baujahr"));

        WebElement fahrzeugart = driver.findElement(By.id("fahrzeugart"));
        Select fahrzeugartSelect = new Select(fahrzeugart);
        fahrzeugartSelect.selectByIndex(1);
        fahrzeugartSelect.selectByIndex(0);

        WebElement submitButton = driver.findElement(By.name("submit"));

        marke.sendKeys("VW");
        model.sendKeys("Tiguan");
        farbe.sendKeys("Rot");
        hoechstgeschwindigkeit.sendKeys("200");
        kennzeichen.sendKeys("TST-PM-1337");
        baujahr.sendKeys("1990");
        submitButton.click();

        WebElement successMsg = driver.findElement(By.id("success"));
        String successMsgText = successMsg.getText();
        assertEquals("Fahrzeug erfolgreich angelegt!", successMsgText);
    }

    @Test
    public void Motorrad_anlegen_test_erfolgreich_mit_pageobject() {
        FahrzeugPage fahrzeugPage = new FahrzeugPage(driver);
        fahrzeugPage.selectFahrzeugart("motorrad").setMarke("VW").setModel("Tiguan").setFarbe("Rot")
            .setHoechstgeschwindigkeit("200").setKennzeichen("TST-PM-1337").setBaujahr("1990").clickSubmitButton();
        String successMsgText = fahrzeugPage.getSuccessMsg();
        assertEquals("Fahrzeug erfolgreich angelegt!", successMsgText);
    }

    @Test
    public void Auto_anlegen_mit_feld_baujahr_test_erfolgreich_mit_pageobject() {
        FahrzeugPage fahrzeugPage = new FahrzeugPage(driver);
        JavascriptExecutor jse = driver;
        jse.executeScript("document.getElementsByClassName(\"visible\")[0].style.visibility ='visible';"
            + "document.getElementsByClassName(\"visible\")[1].style.visibility ='visible'");
        fahrzeugPage.selectFahrzeugart("auto").setMarke("VW").setModel("Tiguan").setFarbe("Rot").setHoechstgeschwindigkeit(
            "200").setKennzeichen("TST-PM-1337").setBaujahr("1990").clickSubmitButton();
        String successMsgText = fahrzeugPage.getSuccessMsg();
        assertEquals("Fahrzeug erfolgreich angelegt!", successMsgText);
    }

    @Test
    public void Motorrad_anlegen_mit_fehler() throws IOException {
        String successMsgText = "";
        FahrzeugPage fahrzeugPage = new FahrzeugPage(driver);
        fahrzeugPage.selectFahrzeugart("motorrad").setMarke("BMW").setModel("R 1300 GS").setFarbe("Blau")
            .setHoechstgeschwindigkeit("250").setKennzeichen("TST-PM-1337").clickSubmitButton();
        try {
            successMsgText = fahrzeugPage.getSuccessMsg();
        } catch (Exception e) {
            takeScreenshot();
        }
        assertEquals("Fahrzeug erfolgreich angelegt!", successMsgText);

        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofMillis(3000));
        wait.pollingEvery(Duration.ofMillis(200));
        wait.ignoring(NoSuchElementException.class);

        // This is how we specify the condition to wait on.
        // This is what we will explore more in this chapter
        wait.until(ExpectedConditions.alertIsPresent());

    }

    private void takeScreenshot() throws IOException {
        File tempScreenshot = driver.getScreenshotAs(OutputType.FILE);

        File screenshotDirectory = new File("screenshots");
        screenshotDirectory.mkdirs();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'_'HHmmss");
        String today = dateFormat.format(new Date());

        File tempScreenshotPfadAsFile = new File(tempScreenshot.getAbsolutePath());
        File zielPfadAsFile = new File(screenshotDirectory, "screenshot_" + today + ".png");
        FileUtils.copyFile(tempScreenshotPfadAsFile, zielPfadAsFile);
    }

}
