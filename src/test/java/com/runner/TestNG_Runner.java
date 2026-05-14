package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		features = "src/test/resources/features/CreateUserCredentials.feature",
		glue = { "com.stepdefinitions", "com.hooks" },
		plugin = {"pretty",
		          "html:target/cucumber-report.html",
		          "json:target/cucumber-report.json",
		          "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		          "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
		monochrome = true)

public class TestNG_Runner extends AbstractTestNGCucumberTests {

}