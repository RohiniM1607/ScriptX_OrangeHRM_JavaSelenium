package com.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.ContactDetailsActions;
import com.actions.DashBoardActions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactDetailsStepDefinition {

    private static final Logger log = LogManager.getLogger(ContactDetailsStepDefinition.class);

    DashBoardActions dashBoardActions;
    ContactDetailsActions contactDetailsActions;

    @When("Employee navigates to Contact Details page")
    public void employee_navigates_to_contact_details_page() {
        log.info("Navigating to Contact Details page from the Dashboard");
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToContactDetails();
        contactDetailsActions = new ContactDetailsActions();
        log.info("Successfully landed on Contact Details page");
    }

    @And("Employee updates contact details from test data")
    public void employee_updates_contact_details_from_test_data() {
        log.info("Updating contact details using test data from properties file");
        contactDetailsActions.updateContactDetails();
        log.info("Contact details fields filled successfully");
    }

    @And("Employee clicks on the Contact Details Save button")
    public void employee_clicks_on_the_contact_details_save_button() {
        log.info("Clicking Save button to submit contact details");
        contactDetailsActions.clickSave();
    }

    @Then("the Contact details should be updated successfully")
    public void the_contact_details_should_be_updated_successfully() {
        log.info("Verifying success message after saving contact details");
        String actual = contactDetailsActions.getSuccessMessage();
        log.info("Success message received: {}", actual);
        Assert.assertEquals(actual, "Success","Expected 'Success' but got '" + actual + "'");
        log.info("Contact details updated successfully as expected");
    }

    @When("Employee clicks on Add Attachment icon")
    public void employee_clicks_on_add_attachment_icon() {
        log.info("Clicking the Add Attachment icon");
        contactDetailsActions.clickAddAttachment();
        log.info("Add Attachment panel opened successfully");
    }

    @And("Employee uploads attachment from test data")
    public void employee_uploads_attachment_from_test_data() {
        log.info("Uploading attachment file via file input");
        contactDetailsActions.uploadAttachment();
        log.info("Attachment file sent to input successfully");
    }

    @And("Employee clicks on Save Attachment button")
    public void employee_clicks_on_save_attachment_button() {
        log.info("Clicking Save button to save the attachment");
        contactDetailsActions.clickSaveAttachment();
    }

    @Then("the Attachment should be uploaded successfully")
    public void the_attachment_should_be_uploaded_successfully() {
        log.info("Verifying success message after attachment upload");
        String actual = contactDetailsActions.getSuccessMessage();
        log.info("Success message received: {}", actual);
        Assert.assertEquals(actual, "Success","Expected 'Success' but got '" + actual + "'");
        log.info("Attachment uploaded successfully as expected");
    }
}