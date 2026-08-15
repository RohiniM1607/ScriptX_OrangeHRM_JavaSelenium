package com.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.DashBoardActions;
import com.actions.DependentsActions;
import com.actions.LoginActions;
import com.pages.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class DependentsStepDefinition {

    private static final Logger log = LogManager.getLogger(DependentsStepDefinition.class);

    LoginActions loginActions;
    LoginPage loginPage;
    DashBoardActions dashBoardActions;
    DependentsActions dependentsActions;

    @When("Employee navigates to Dependents page")
    public void employee_navigates_to_dependents_page() {
        log.info("Navigating to Dependents page");
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToMyInfo();
        dashBoardActions.navigateToDependents();
        dependentsActions = new DependentsActions();
        log.info("Successfully landed on Dependents page");
    }

    @And("Employee clicks on Add icon in Dependents section")
    public void employee_clicks_on_add_icon_in_dependents_section() {
        log.info("Clicking Add icon in Dependents section");
        dependentsActions.clickAddIcon();
    }

    @And("Employee fills dependent details with {string} {string} and {string}")
    public void employee_fills_dependent_details_with(String name, String relationship, String dob) {
        log.info("Filling dependent details - Name: {}, Relationship: {}, DOB: {}", name, relationship, dob);
        dependentsActions.fillDependentDetails(name, relationship, dob);
    }

    @And("Employee clicks on Save button in Dependents section")
    public void employee_clicks_on_save_button_in_dependents_section() {
        log.info("Clicking Save button in Dependents section");
        dependentsActions.clickSave();
    }

    @Then("Dependent should be saved successfully")
    public void dependent_should_be_saved_successfully() {
        log.info("Verifying success message after saving dependent");
        String actual = dependentsActions.getSuccessMessage();
        log.info("Success message received: {}", actual);
        Assert.assertEquals(actual, "Success", "Dependent was not saved successfully");
    }

    @When("Employee moves to Dependents page")
    public void employee_moves_to_dependents_page() {
        log.info("Moving to Dependents page");
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToMyInfo();
        dashBoardActions.navigateToDependents();
        dependentsActions = new DependentsActions();
    }

    @And("Employee clicks on Add button in the Attachments section")
    public void employee_clicks_on_add_button_in_the_attachments_section() {
        log.info("Clicking Add button in Attachments section");
        dependentsActions.clickAttachmentAddButton();
    }

    @And("Employee uploads attachment with details")
    public void employee_uploads_attachment_with_details(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String fileName = data.get(0).get("FileName").trim();
        String comment = data.get(0).get("Comment").trim();
        log.info("Uploading attachment - FileName: {}, Comment: {}", fileName, comment);

        // Fix: fileName was extracted but never passed to the method.
        // uploadAttachment() was called with no arguments — the method
        // ignored the DataTable value and used a hardcoded filename instead.
        dependentsActions.uploadAttachment(fileName);
    }

    @And("Employee clicks on Save button in Attachments section")
    public void employee_clicks_on_save_button_in_attachments_section() {
        log.info("Clicking Save button in Attachments section");
        dependentsActions.clickSaveAttachment();
    }

    @Then("Attachment should be uploaded successfully")
    public void attachment_should_be_uploaded_successfully() {
        log.info("Verifying success message after attachment upload");
        String actual = dependentsActions.getAttachmentSuccessMessage();
        log.info("Success message received: {}", actual);
        Assert.assertEquals(actual, "Success", "Attachment was not uploaded successfully");
    }

    @When("Employee navigates to the Dependents page")
    public void employee_navigates_to_the_dependents_page() {
        log.info("Navigating to Dependents page for invalid attachment test");
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToMyInfo();
        dashBoardActions.navigateToDependents();
        dependentsActions = new DependentsActions();
    }

    @And("Employee clicks on Add button in the Attachments")
    public void employee_clicks_on_add_button_in_the_attachments() {
        log.info("Clicking Add button in Attachments");
        dependentsActions.clickAttachmentAddButton();
    }

    @And("Employee uploads invalid attachment details")
    public void employee_uploads_invalid_attachment_details(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String fileName = data.get(0).get("FileName").trim();
        log.info("Uploading invalid attachment - FileName: {}", fileName);

        // Fix: fileName was extracted but never passed to the method.
        // uploadInvalidAttachment() was called with no arguments — the method
        // had Large_file.txt hardcoded and ignored what the feature file said.
        dependentsActions.uploadInvalidAttachment(fileName);
    }

    @Then("An error message should be displayed")
    public void an_error_message_should_be_displayed() {
        log.info("Verifying error message for invalid file size");
        String actual = dependentsActions.getFileSizeErrorMessage();
        log.info("Error message received: {}", actual);
        Assert.assertTrue(actual.contains("Attachment Size Exceeded"),
                "Expected 'Attachment Size Exceeded' but got: " + actual);
    }
}