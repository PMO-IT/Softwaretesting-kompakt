package de.pmoit.grundlagen.softwaretesting.test.cucumber.runner;

import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("de.pmoit.grundlagen.softwaretesting")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "de.pmoit.grundlagen.softwaretesting.test.cucumber.steps")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME,
    value = "src/test/java/de/pmoit/grundlagen/softwaretesting/test/cucumber/features/")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber/html.html,json:target/json/json.json")
public class MainRunner {

}
