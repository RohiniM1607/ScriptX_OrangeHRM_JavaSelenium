package com.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.DashBoardActions;
import com.actions.LoginActions;
import com.actions.PersonalDetailsActions;
import com.pages.LoginPage;
import com.utilities.HelperClass;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PersonalDetailsStepDefinition {

    private static final Logger log = LogManager.getLogger(PersonalDetailsStepDefinition.class);

    LoginActions loginActions;
    DashBoardActions dashBoardActions;
    PersonalDetailsActions personalDetailsActions;
    LoginPage loginPage = new LoginPage();
    HelperClass helper = new HelperClass();

    @Given("Employee is on OrangeHRM login page")
    public void employee_is_on_orangehrm_login_page() {
        log.info("Navigating to OrangeHRM login page");
        loginActions = new LoginActions();
        loginPage = new LoginPage();
    }

     @When("Employee enters valid username and password")
    public void employee_enters_valid_username_and_password(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        String username = credentials.get(0).get("username").trim();
        String password = credentials.get(0).get("password").trim();
        log.info("Entering username: {}", username);
        loginPage.enterUsername(username);
        log.info("Entering password");
        loginPage.enterPassword(password);
    }

    @And("Employee clicks on login button")
    public void employee_clicks_on_login_button() {
        log.info("Clicking the Login button");
        loginActions.clickLogin();
    }

    @Given("the Employee is on the Dashboard page")
    public void the_employee_is_on_the_dashboard_page() {
        log.info("Verifying that the Dashboard is displayed after login");
        dashBoardActions = new DashBoardActions();
        boolean isDashboardVisible = dashBoardActions.isDashboardDisplayed();
        Assert.assertTrue(isDashboardVisible, "Dashboard is not displayed!");
        log.info("Dashboard is displayed successfully");
    }

    @When("Employee navigates to My Info page")
    public void employee_navigates_to_my_info_page() {
        log.info("Navigating to My Info page from the Dashboard");
        dashBoardActions.navigateToMyInfo();
        personalDetailsActions = new PersonalDetailsActions();
        log.info("Successfully landed on My Info - Personal Details page");
    }

    @And("Employee updates personal details with following data")
    public void employee_updates_personal_details_with_following_data(DataTable dataTable) {
        log.info("Updating personal details with the provided data");
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        personalDetailsActions.updatePersonalDetails(data);
        log.info("Personal details fields filled successfully");
    }

    @And("Employee clicks on Save button")
    public void employee_clicks_on_save_button() {
        log.info("Clicking Save button to submit personal details");
        personalDetailsActions.clickSave();
    }

    @Then("Personal details should be updated successfully")
    public void personal_details_should_be_updated_successfully() {
        log.info("Verifying success message after saving personal details");
        String message = personalDetailsActions.successisDisplayed();
        log.info("Success message received: {}", message);
        Assert.assertEquals(message, "Success", "Successfully updated");
        log.info("Personal details updated successfully as expected");
    }

    @When("Employee navigates to Profile Picture page")
    public void employee_navigates_to_profile_picture_page() {
        log.info("Navigating to the Profile Picture page via My Info");
        dashBoardActions.navigateToProfilePicture();
        personalDetailsActions = new PersonalDetailsActions();
        log.info("Successfully landed on the Profile Picture page");
    }

    @When("Employee uploads a profile picture with {string}")
    public void employee_uploads_a_profile_picture_with(String filePath) {
        log.info("Uploading profile picture using file: {}", filePath);
        personalDetailsActions.uploadProfilePicture(filePath);
        log.info("Profile picture sent to file input successfully");
    }

   @When("Employee clicks on the Save button")
    public void employee_clicks_on_the_save_button() {
        log.info("Clicking Save button to upload the profile picture");
        personalDetailsActions.clickSave1();
    }

    @Then("Profile picture should be uploaded successfully with {string}")
    public void profile_picture_should_be_uploaded_successfully_with(String expectedMessage) {
        log.info("Verifying success message after profile picture upload");
        String actual = personalDetailsActions.getSuccessMessage();
        log.info("Success message received: {}", actual);
        Assert.assertEquals(actual, expectedMessage,
                "Expected success message '" + expectedMessage + "' but got '" + actual + "'");
        log.info("Profile picture uploaded successfully as expected");
    }
}