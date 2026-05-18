package com.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.actions.DashBoardActions;
import com.actions.LoginActions;
import com.actions.ProfilePictureActions;
import com.pages.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
        String username = credentials.get(0).get("username").trim();
        String password = credentials.get(0).get("password").trim();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
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
    	dashBoardActions.navigateToMyInfo();
        dashBoardActions.navigateToProfilePicture();
        profilePictureActions = new ProfilePictureActions();
    }

    @When("Employee uploads a profile picture with following data")
    public void employee_uploads_a_profile_picture_with_following_data(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String filePath = data.get(0).get("FilePath").trim();
        profilePictureActions.uploadProfilePicture(filePath);
    }

    @When("Employee clicks on the Save button")
    public void employee_clicks_on_the_save_button() {
        profilePictureActions.clickSave();
    }

    @Then("Profile picture should be uploaded successfully")
    public void profile_picture_should_be_uploaded_successfully() {
        String message = profilePictureActions.getSuccessMessage();
        Assert.assertEquals(message, "Success", "Sucessfully Saved");
    }
}