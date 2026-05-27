package com.stepdefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.DashBoardActions;
import com.actions.EmergencyContactsActions;
import com.actions.LoginActions;
import com.pages.LoginPage;
import com.utilities.DP_Excel;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmergencyContactsStepDefinition {

    private static final Logger log = LogManager.getLogger(EmergencyContactsStepDefinition.class);

    LoginActions loginActions;
    LoginPage loginPage;
    DashBoardActions dashBoardActions;
    EmergencyContactsActions emergencyContactsActions;

    @Given("Employee launches the OrangeHRM login")
    public void employee_launches_the_orange_hrm_login() {
        log.info("Navigating to OrangeHRM login page");
        loginActions = new LoginActions();
        loginPage = new LoginPage();
    }

    @When("Employee enters the valid {string} and {string}")
    public void employee_enters_the_valid_and(String string, String string2, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        String username = credentials.get(0).get("username").trim();
        String password = credentials.get(0).get("password").trim();
        log.info("Entering username: {}", username);
        loginPage.enterUsername(username);
        log.info("Entering password");
        loginPage.enterPassword(password);
    }

    @When("the Employee clicks on the login link")
    public void the_employee_clicks_on_the_login_link() {
        log.info("Clicking the Login button");
        loginActions.clickLogin();
    }

    @When("Employee is on the Dashboard page")
    public void employee_is_on_the_dashboard_page() {
        log.info("Verifying Dashboard is displayed");
        dashBoardActions = new DashBoardActions();
        boolean isDashboardVisible = dashBoardActions.isDashboardDisplayed();
        Assert.assertTrue(isDashboardVisible, "Dashboard is not displayed!");
        log.info("Dashboard displayed successfully");
    }

    @When("Employee navigates to Emergency Contacts page")
    public void employee_navigates_to_emergency_contacts_page() {
        log.info("Navigating to Emergency Contacts page");
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToEmergencyContacts();
        emergencyContactsActions = new EmergencyContactsActions();
        log.info("Successfully landed on Emergency Contacts page");
    }

    @When("Employee clicks on the Add Emergency Contact icon")
    public void employee_clicks_on_the_add_emergency_contact_icon() {
        log.info("Clicking the Add Emergency Contact button");
        emergencyContactsActions.clickAddEmergencyContact();
        log.info("Emergency Contact form opened");
    }

    @When("Employee fills emergency contact details from {string}")
    public void employee_fills_emergency_contact_details_from(String Sheet1) throws IOException {
        log.info("Reading emergency contact data from Excel sheet: {}", Sheet1);
        DP_Excel excel = new DP_Excel();
        Object[][] data = excel.getExcelData( "src/test/resources/testdata/EmergencyContactsData.xlsx", Sheet1);
        String name          = getCellValue(data[0][0]);
        String relationship  = getCellValue(data[0][1]);
        String homeTelephone = getCellValue(data[0][2]);
        String mobile        = getCellValue(data[0][3]);
        String workTelephone = getCellValue(data[0][4]);
        log.info("Filling emergency contact: Name={}, Relationship={}", name, relationship);
        emergencyContactsActions.fillEmergencyContactDetails(name, relationship, homeTelephone, mobile, workTelephone);
        log.info("Emergency contact form filled successfully");
    }

    @When("Employee clicks on Save Emergency Contact button")
    public void employee_clicks_on_save_emergency_contact_button() {
        log.info("Clicking Save button for Emergency Contact");
        emergencyContactsActions.clickSaveEmergencyContact();
    }

    @Then("the Emergency Contact should be saved successfully")
    public void the_emergency_contact_should_be_saved_successfully() {
        log.info("Verifying success message after saving emergency contact");
        String actual = emergencyContactsActions.getSuccessMessage();
        log.info("Success message received: {}", actual);
        Assert.assertEquals(actual, "Success","Expected 'Success' but got '" + actual + "'");
        log.info("Emergency contact saved successfully");
    }

    @When("Employee clicks on the Add Attachment icon in Emergency Contacts")
    public void employee_clicks_on_the_add_attachment_icon_in_emergency_contacts() {
        log.info("Clicking Add Attachment icon in Emergency Contacts");
        emergencyContactsActions.clickAddAttachment();
        log.info("Attachment upload panel opened");
    }
    
    @When("Employee uploads an attachment in {string}")
    public void employee_uploads_an_attachment_in(String section) {
        log.info("Uploading attachment in: {}", section);
        emergencyContactsActions.uploadAttachment();
        log.info("Attachment file sent successfully in: {}", section);
    }


    @When("Employee clicks on Save Attachment button in Emergency Contacts")
    public void employee_clicks_on_save_attachment_button_in_emergency_contacts() {
        log.info("Clicking Save button for attachment");
        emergencyContactsActions.clickSaveAttachment();
    }

    @Then("the Attachment in Emergency Contacts should be saved successfully")
    public void the_attachment_in_emergency_contacts_should_be_saved_successfully() {
        log.info("Verifying success message after saving attachment");
        String actual = emergencyContactsActions.getSuccessMessage();
        log.info("Success message received: {}", actual);
        Assert.assertEquals(actual, "Success","Expected 'Success' but got '" + actual + "'");
        log.info("Attachment saved successfully");
    }

    private String getCellValue(Object value) {
        if (value == null) return "";
        return value.toString().trim();
    }
}
