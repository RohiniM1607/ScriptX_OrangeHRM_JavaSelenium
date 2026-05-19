package com.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.actions.DashBoardActions;
import com.actions.LoginActions;
import com.actions.ProfilePictureActions;
import com.pages.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class ProfilePictureStepDefinition {

    LoginActions loginActions;
    DashBoardActions dashBoardActions;
    ProfilePictureActions profilePictureActions;
    LoginPage loginPage;

    @Given("Employee is on OrangeHRM Profile Picture login page")
    public void employee_is_on_orange_hrm_profile_picture_login_page() {
        loginActions = new LoginActions();
        loginPage = new LoginPage();
    }

    @When("Employee enters valid username and password for profile")
    public void employee_enters_valid_username_and_password_for_profile(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        loginPage.enterUsername(credentials.get(0).get("username").trim());
        loginPage.enterPassword(credentials.get(0).get("password").trim());
    }

    @When("Employee clicks on login")
    public void employee_clicks_on_login() {
        loginActions.clickLogin();
    }

    @When("the Employee is on the OrangeHRM Dashboard page")
    public void the_employee_is_on_the_orange_hrm_dashboard_page() {
        dashBoardActions = new DashBoardActions();
        Assert.assertTrue(dashBoardActions.isDashboardDisplayed(), "Dashboard is not displayed!");
    }

    @When("Employee navigates to Profile Picture page")
    public void employee_navigates_to_profile_picture_page() {
        dashBoardActions.navigateToProfilePicture();
        profilePictureActions = new ProfilePictureActions();
    }

    @When("Employee uploads a profile picture with {string}")
    public void employee_uploads_a_profile_picture_with(String filePath) {
        profilePictureActions.uploadProfilePicture(filePath);
    }

    @When("Employee clicks on the Save button")
    public void employee_clicks_on_the_save_button() {
        profilePictureActions.clickSave();
    }

    @Then("Profile picture should be uploaded successfully with {string}")
    public void profile_picture_should_be_uploaded_successfully_with(String expectedMessage) {
        String actual = profilePictureActions.getSuccessMessage();
        Assert.assertEquals(actual, expectedMessage,
                "Expected success message '" + expectedMessage + "' but got '" + actual + "'");
    }
    
    @When("Employee moves to Profile Picture page")
    public void employee_moves_to_profile_picture_page() {
        dashBoardActions.navigateToProfilePicture();
        profilePictureActions = new ProfilePictureActions();
    }

    @When("Employee uploads a profile picture using {string}")
    public void employee_uploads_a_profile_picture_using(String filePath) {
        profilePictureActions.uploadProfilePicture(filePath);
    }

    @When("Employee forgot to click on the Save button")
    public void employee_forgot_to_click_on_the_save_button() {

    }

    @Then("Profile picture should not be updated with {string}")
    public void profile_picture_should_not_be_updated_with(String message) {
        Assert.assertFalse(profilePictureActions.isSuccessMessageDisplayed(),
                "Success message appeared even though Save was not clicked!");
    }
}