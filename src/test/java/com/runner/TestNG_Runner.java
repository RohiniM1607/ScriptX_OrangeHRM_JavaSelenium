package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		features = "src/test/resources/features/CreateUserCredentials.feature",
		glue = { "com.stepdefinitions", "com.hooks" },
		plugin = {"pretty",
			   	  "html:target/cucumber-report.html" },
		monochrome = true)

public class TestNG_Runner extends AbstractTestNGCucumberTests {

}