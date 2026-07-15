package com.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

<<<<<<< HEAD
<<<<<<< HEAD
        features = "src/test/resources/features/AdminLeaveManagement_LeaveApprovel.feature",
=======
        features = "src/test/resources/features/",
>>>>>>> 35d5476099b8f7a352de9ebc38e47efc349be1c5
=======
        features = "src/test/resources/features/",
>>>>>>> 8b5629efbd301692b7211fa875f1343619a651d8
        glue = {"com.stepdefinitions", "com.hooks"},
        plugin = { 
                "pretty",
                "html:target/CucumberReports/Cucumber.html",
                "json:target/CucumberReports/Cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        publish = true
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
