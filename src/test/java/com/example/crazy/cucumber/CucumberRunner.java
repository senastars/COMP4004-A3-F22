package com.example.crazy.cucumber;

//import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

import io.cucumber.core.options.Constants;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/java/com/example/crazy/cucumber/features")
//@SelectFile("/src/test/java/cucumber/features/online.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.crazy.cucumber")
@ConfigurationParameter(key = Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
//@CucumberContextConfiguration
//@SpringBootTest(classes = TestConfiguration.class)
public class CucumberRunner {
}