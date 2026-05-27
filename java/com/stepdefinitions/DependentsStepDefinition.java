package com.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.DashBoardActions;
import com.actions.DependentsActions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DependentsStepDefinition {

    private static final Logger log = LogManager.getLogger(DependentsStepDefinition.class);

    DashBoardActions dashBoardActions;
    DependentsActions dependentsActions;

    @When("Employee navigates to Dependents page")
    public void employee_navigates_to_dependents_page() {
        log.info("Navigating to Dependents page via My Info");
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToMyInfo();
        dependentsActions = new DependentsActions();
        log.info("Successfully landed on Dependents page");
    }

    @And("Employee clicks on Add icon in Dependents section")
    public void employee_clicks_on_add_icon_in_dependents_section() {
        log.info("Clicking Add icon in Dependents section");
        dependentsActions.clickAddIcon();
        log.info("Add form opened successfully");
    }

    @And("Employee fills dependent details with {string} {string} and {string}")
    public void employee_fills_dependent_details_with(String name, String relationship,
                                                       String dateOfBirth) {
        log.info("Filling dependent details — Name: {}, Relationship: {}, DOB: {}",
                name, relationship, dateOfBirth);
        dependentsActions.fillDependentDetails(name, relationship, dateOfBirth);
        log.info("Dependent details filled successfully");
    }

    @And("Employee clicks on Save button in Dependents section")
    public void employee_clicks_on_save_button_in_dependents_section() {
        log.info("Clicking Save button in Dependents section");
        dependentsActions.clickSave();
    }

    @Then("Dependent should be saved successfully")
    public void dependent_should_be_saved_successfully() {
        log.info("Verifying success message after saving dependent");
        String message = dependentsActions.getSuccessMessage();
        log.info("Success message received: {}", message);
        Assert.assertEquals(message, "Success", "Dependent was not saved successfully");
        log.info("Dependent saved successfully as expected");
    }
}