package com.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.actions.ContactDetailsActions;
import com.actions.DashBoardActions;
import com.actions.LoginActions;
import com.pages.LoginPage;
import com.utilities.ConfigReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactDetailsStepDefinition {

    LoginActions loginActions;
    DashBoardActions dashBoardActions;
    ContactDetailsActions contactDetailsActions;
    LoginPage loginPage;
    ConfigReader testData = new ConfigReader("testData.properties");

    @Given("Employee is on OrangeHRM Contact Details page")
    public void employee_is_on_orange_hrm_contact_details_page() {
        loginActions = new LoginActions();
        loginPage = new LoginPage();
    }

    @When("Employee enters username and password for contact details")
    public void employee_enters_username_and_password_for_contact_details(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        String username = credentials.get(0).get("username").trim();
        String password = credentials.get(0).get("password").trim();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("Employee clicks on Contact Details login button")
    public void employee_clicks_on_contact_details_login_button() {
        loginActions.clickLogin();
    }

    @When("the Employee is on the Contact Details Dashboard page")
    public void the_employee_is_on_the_contact_details_dashboard_page() {
        dashBoardActions = new DashBoardActions();
        Assert.assertTrue(dashBoardActions.isDashboardDisplayed(),
            "Dashboard is not displayed after login!");
    }

    @When("the Employee navigates to Contact Details page")
    public void the_employee_navigates_to_contact_details_page() {
        dashBoardActions.navigateToContactDetails();
        contactDetailsActions = new ContactDetailsActions();
    }

    @When("Employee updates contact details from test data")
    public void employee_updates_contact_details_from_test_data() {
        contactDetailsActions.updateContactDetails();
    }

    @When("Employee clicks on the Contact Details Save button")
    public void employee_clicks_on_the_contact_details_save_button() {
        contactDetailsActions.clickSave();
    }

    @Then("the Contact details should be updated successfully")
    public void the_contact_details_should_be_updated_successfully() {
        String message = contactDetailsActions.getSuccessMessage();
        Assert.assertEquals(message, "Success", "Contact details save failed!");
    }

    @When("Employee clicks on Add Attachment icon")
    public void employee_clicks_on_add_attachment_icon() {
        contactDetailsActions.clickAddAttachment();
    }

    @When("Employee uploads attachment from test data")
    public void employee_uploads_attachment_from_test_data() {
        contactDetailsActions.uploadAttachment();
    }

    @When("Employee clicks on Save Attachment button")
    public void employee_clicks_on_save_attachment_button() {
        contactDetailsActions.clickSaveAttachment();
    }

    @Then("the Attachment should be uploaded successfully")
    public void the_attachment_should_be_uploaded_successfully() {
        String message = contactDetailsActions.getSuccessMessage();
        Assert.assertEquals(message, "Success", "Attachment upload failed!");
    }
}