package de.pmoit.grundlagen.softwaretesting.test.cucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class FahrzeugAnlegenTest {
    private static FirefoxDriver driver;

    @BeforeAll
    public static void startBrowser() {
        driver = new FirefoxDriver(); // Alternativ: new ChromeDriver();
    }

    @Given("der Browser wird mit der URL {string} geöffnet")
    public void der_browser_wird_mit_der_url_geöffnet(String url) {
        driver.get(url);
    }

    @Given("der Titel der URL lautet {string}")
    public void der_titel_der_url_lautet(String urlTitel) {
        assertEquals(driver.getTitle(), urlTitel);
    }

    @When("ein blauer Tesla Model {int} angelegt wird")
    public void ein_blauer_Tesla_Model_angelegt_wird() {
        // ...
    }

    @When("ein roter VW Tiguan angelegt wird")
    public void ein_roter_VW_Tiguan_angelegt_wird() {
        // ...
    }

    @When("ein neues Fahrzeug angelegt wird")
    public void ein_neues_fahrzeug_angelegt_wird(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        String fahrzeugtyp = data.get(0).get(0);
        String farbe = data.get(0).get(1);
        // ...
    }

    @When("ein neues Fahrzeug {string} der Marke {string} angelegt wird, mit der Farbe {string} und dem Model {string}")
    public void ein_neues_fahrzeug_der_marke_angelegt_wird_mit_der_farbe_und_dem_model(String fahrzeugart, String marke,
        String farbe, String model) {

    }

    @When("der submit button geklickt wird")
    public void der_submit_button_geklickt_wird() {
    }

    @Then("wird das Fahrzeug angelegt")
    public void wird_das_fahrzeug_angelegt() {
        // ...
    }

    @Then("eine Erfolgsmeldung erscheint")
    public void eine_erfolgsmeldung_erscheint() {
    }

}
