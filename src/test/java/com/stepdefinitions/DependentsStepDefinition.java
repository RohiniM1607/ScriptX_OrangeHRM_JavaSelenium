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

    @Given("Employee launches OrangeHRM login page")
    public void employee_launches_orange_hrm_login_page() {
        log.info("Launching OrangeHRM login page");
        loginActions = new LoginActions();
        loginPage = new LoginPage();
    }

    @When("Employee enters valid {string} and {string} using employee credentials")
    public void employee_enters_valid_and_using_employee_credentials(String userKey, String passKey, DataTable dataTable) {

        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);

        String username = credentials.get(0).get(userKey).trim();
        String password = credentials.get(0).get(passKey).trim();

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("Employee clicks on login link")
    public void employee_clicks_on_login_link() {
        loginActions.clickLogin();
    }

    @And("Employee is on Dashboard page")
    public void employee_is_on_dashboard_page() {
        dashBoardActions = new DashBoardActions();
        Assert.assertTrue(dashBoardActions.isDashboardDisplayed(), "Dashboard not displayed");
    }

    @When("Employee navigates to Dependents page")
    public void employee_navigates_to_dependents_page() {
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToMyInfo();
        dashBoardActions.navigateToDependents();

        dependentsActions = new DependentsActions();
    }

    @And("Employee clicks on Add icon in Dependents section")
    public void employee_clicks_on_add_icon_in_dependents_section() {
        dependentsActions.clickAddIcon();
    }

    @And("Employee fills dependent details with {string} {string} and {string}")
    public void employee_fills_dependent_details_with(String name, String relationship, String dob) {
        dependentsActions.fillDependentDetails(name, relationship, dob);
    }

    @And("Employee clicks on Save button in Dependents section")
    public void employee_clicks_on_save_button_in_dependents_section() {
        dependentsActions.clickSave();
    }

    @Then("Dependent should be saved successfully")
    public void dependent_should_be_saved_successfully() {
        String actual = dependentsActions.getSuccessMessage();
        Assert.assertEquals(actual, "Success");
    }

    @When("Employee moves to Dependents page")
    public void employee_moves_to_dependents_page() {
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToMyInfo();
        dashBoardActions.navigateToDependents();

        dependentsActions = new DependentsActions();
    }

    @And("Employee clicks on Add button in the Attachments section")
    public void employee_clicks_on_add_button_in_the_attachments_section() {
        dependentsActions.clickAttachmentAddButton();
    }

    @And("Employee uploads attachment with details")
    public void employee_uploads_attachment_with_details(DataTable dataTable) {

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        String fileName = data.get(0).get("FileName").trim();
        String comment = data.get(0).get("Comment").trim();

        dependentsActions.uploadAttachment();
    }

    @And("Employee clicks on Save button in Attachments section")
    public void employee_clicks_on_save_button_in_attachments_section() {
        dependentsActions.clickSaveAttachment();
    }

    @Then("Attachment should be uploaded successfully")
    public void attachment_should_be_uploaded_successfully() {
        String actual = dependentsActions.getAttachmentSuccessMessage();
        Assert.assertEquals(actual, "Success");
    }

    @When("Employee navigates to the Dependents page")
    public void employee_navigates_to_the_dependents_page() {
        dashBoardActions = new DashBoardActions();
        dashBoardActions.navigateToMyInfo();
        dashBoardActions.navigateToDependents();

        dependentsActions = new DependentsActions();
    }

    @And("Employee clicks on Add button in the Attachments")
    public void employee_clicks_on_add_button_in_the_attachments() {
        dependentsActions.clickAttachmentAddButton();
    }

    @And("Employee uploads invalid attachment details")
    public void employee_uploads_invalid_attachment_details(DataTable dataTable) {

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        String fileName = data.get(0).get("FileName").trim();

        dependentsActions.uploadInvalidAttachment();
    }

    @Then("An error message should be displayed")
    public void an_error_message_should_be_displayed() {
        String actual = dependentsActions.getFileSizeErrorMessage();

        Assert.assertTrue(actual.contains("Attachment Size Exceeded"));
    }
}