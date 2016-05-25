package ru.markelov.app;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"
        ,glue = {"ru.markelov.app.stepDefinitions"}
)
public class RunCucumber {
}
