package com.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
<<<<<<< HEAD

        features = "src/test/resources/features/Add_leave_entitlement.feature",
=======
        features = "src/test/resources/features/",
>>>>>>> 3595cdf0774fdde6205e38eb6431db4db9adac91
        glue = {"com.stepdefinitions", "com.hooks"},
        plugin = { 
                "pretty",
                "html:target/CucumberReports/Cucumber.html",
                "json:target/CucumberReports/Cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        publish = true,
        tags = "@Rohini"
)

public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
