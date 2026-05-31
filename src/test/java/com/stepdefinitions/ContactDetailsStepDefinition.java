package com.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.ContactDetailsActions;
import com.actions.DashBoardActions;
import com.actions.LoginActions;
import com.pages.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactDetailsStepDefinition {

    private static final Logger log = LogManager.getLogger(ContactDetailsStepDefinition.class);

    LoginActions loginActions;
    LoginPage loginPage;
    DashBoardActions dashBoardActions;
    ContactDetailsActions contactDetailsActions;

    @Given("Employee is on the OrangeHRM login page")
    public void employee_is_on_the_orange_hrm_login_page() {
        log.info("Navigating to OrangeHRM login page");
        loginActions = new LoginActions();
        loginPage = new LoginPage();
    }

    @When("Employee enters valid {string} and {string}")
    public void employee_enters_valid_and(String string, String string2,
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        String username = credentials.get(0).get("username").trim();
        String password = credentials.get(0).get("password").trim();
        log.info("Entering username: {}", username);
        loginPage.enterUsername(username);
        log.info("Entering password");
        loginPage.enterPassword(password);
    }

    @When("Employee clicks on the login button")
    public void employee_clicks_on_the_login_button() {
        log.info("Clicking the Login button");
        loginActions.clickLogin();
    }

    @When("the Employee is on Dashboard page")
    public void the_employee_is_on_dashboard_page() {
        log.info("Verifying Dashboard is displayed");
        dashBoardActions = new DashBoardActions();
        boolean isDashboardVisible = dashBoardActions.isDashboardDisplayed();
        Assert.assertTrue(isDashboardVisible, "Dashboard is not displayed!");
        log.info("Dashboard displayed successfully");
    }

    @When("the Employee navigates to Contact Details page")
    public void the_employee_navigates_to_contact_details_page() {
        log.info("Navigating to Contact Details page from the Dashboard");
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToContactDetails();
        contactDetailsActions = new ContactDetailsActions();
        log.info("Successfully landed on Contact Details page");
    }

    @When("Employee updates contact details from {string}")
    public void employee_updates_contact_details_from(String source) {
        log.info("Updating contact details from: {}", source);
        contactDetailsActions.updateContactDetails();
        log.info("Contact details fields filled successfully from: {}", source);
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
        Assert.assertEquals(actual, "Success", "Expected 'Success' but got '" + actual + "'");
        log.info("Contact details updated successfully as expected");
    }

    @When("Employee navigates to Contact Details page")
    public void employee_navigates_to_contact_details_page() {
        log.info("Navigating to Contact Details page from the Dashboard");
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToContactDetails();
        contactDetailsActions = new ContactDetailsActions();
        log.info("Successfully landed on Contact Details page");
    }

    @When("Employee clicks on Add Attachment icon")
    public void employee_clicks_on_add_attachment_icon() {
        log.info("Clicking the Add Attachment icon");
        contactDetailsActions.clickAddAttachment();
        log.info("Add Attachment panel opened successfully");
    }

    @When("Employee uploads the {string}")
    public void employee_uploads_the(String fileName) {
        log.info("Uploading file: {}", fileName);
        contactDetailsActions.uploadAttachment();
        log.info("File uploaded successfully: {}", fileName);
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
        Assert.assertEquals(actual, "Success", "Expected 'Success' but got '" + actual + "'");
        log.info("Attachment uploaded successfully as expected");
    }
}