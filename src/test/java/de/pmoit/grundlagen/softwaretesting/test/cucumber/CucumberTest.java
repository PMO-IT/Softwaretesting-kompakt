package de.pmoit.grundlagen.softwaretesting.test.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;


public class CucumberTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Einmal bevor ein Szenario ausgeführt ist");
    }

    @Before(order = 1)
    public void before1() {
        System.out.println("Einmal vor jedem ersten Schritt eines Szenarios - Reihenfolge 1");
    }

    @Before(order = 2)
    public void before2() {
        System.out.println("Einmal vor jedem ersten Schritt eines Szenarios - Reihenfolge 2");
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("Vor jedem einzelnen Schritt");
    }

    @Given("Example")
    public void example() {
        System.out.println("Test");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Einmal nachdem ein Szenario ausgeführt ist");
    }

    @After
    public void after() {
        System.out.println("Einmal nach dem letzten Schritt eines Szenarios");
    }

    @AfterStep
    public void afterStep() {
        System.out.println("Nach jedem einzelnen Schritt");
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            // Write Email with errorcode
        }

        if (scenario.getName().equals("Example")) {
            // Do something
        }
    }

}
