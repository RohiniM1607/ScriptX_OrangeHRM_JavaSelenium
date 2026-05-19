package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features/PIM.feature", 
		glue = { "com.stepdefinitions","com.hooks" }, 
		plugin = { "pretty", "html:target/CucumberReports/Cucumber.html",
						"json:target/CucumberReports/Cucumber.json",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" },

		monochrome = true,

		publish = true)

public class TestRunner extends AbstractTestNGCucumberTests {

}
