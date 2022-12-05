package crazyeights.cucumber;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/java/crazyeights/cucumber/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "crazyeights.cucumber")
public class CucumberRunner {
}