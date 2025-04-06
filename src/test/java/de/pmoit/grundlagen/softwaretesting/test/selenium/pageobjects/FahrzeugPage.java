package de.pmoit.grundlagen.softwaretesting.test.selenium.pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FahrzeugPage extends PageObject {

    @FindBy(id = "marke")
    private WebElement marke;

    @FindBy(id = "model")
    private WebElement model;

    @FindBy(id = "farbe")
    private WebElement farbe;

    @FindBy(id = "hoechstgeschwindigkeit")
    private WebElement hoechstgeschwindigkeit;

    @FindBy(id = "kennzeichen")
    private WebElement kennzeichen;

    @FindBy(id = "fahrzeugart")
    private WebElement fahrzeugart;

    @FindBy(id = "baujahr")
    private WebElement baujahr;

    @FindBy(id = "success")
    private WebElement success;

    @FindBy(className = "submit")
    private WebElement submit;

    public String getSuccessMsg() {
        return success.getText();
    }

    public void clickSubmitButton() {
        submit.click();
    }

    public FahrzeugPage setMarke(String marke) {
        this.marke.sendKeys(marke);
        return this;
    }

    public FahrzeugPage setModel(String model) {
        this.model.sendKeys(model);
        return this;
    }

    public FahrzeugPage setFarbe(String farbe) {
        this.farbe.sendKeys(farbe);
        return this;
    }

    public FahrzeugPage setHoechstgeschwindigkeit(String hoechstgeschwindigkeit) {
        this.hoechstgeschwindigkeit.clear();
        this.hoechstgeschwindigkeit.sendKeys(hoechstgeschwindigkeit);
        return this;
    }

    public FahrzeugPage setKennzeichen(String kennzeichen) {
        this.kennzeichen.sendKeys(kennzeichen);
        return this;
    }

    public WebElement getBaujahrWebElement() {
        return baujahr;
    }

    public FahrzeugPage setBaujahr(String baujahr) {
        WebDriverWait expliziteswarten = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement baujahrMitWait = expliziteswarten.until(ExpectedConditions.elementToBeClickable(this.baujahr));
        baujahrMitWait.sendKeys(baujahr);
        return this;
    }

    public FahrzeugPage selectFahrzeugart(String fahrzeugart) {
        Select fahrzeugartSelect = new Select(this.fahrzeugart);
        fahrzeugartSelect.selectByValue(fahrzeugart);
        return this;
    }

    public FahrzeugPage(WebDriver driver) {
        super(driver);
    }
}
