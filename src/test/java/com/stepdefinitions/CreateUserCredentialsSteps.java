package com.stepdefinitions;

import org.testng.Assert;

import com.actions.CreateUserCredentialsActions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateUserCredentialsSteps {

    CreateUserCredentialsActions createUserActions;

    @Given("user is on OrangeHRM Admin User Management page")
    public void user_is_on_orange_hrm_admin_user_management_page() {
        createUserActions = new CreateUserCredentialsActions();
        createUserActions.navigateToAdminUserManagementPage();
    }

    @When("user clicks on Add button")
    public void user_clicks_on_add_button() {
        createUserActions.clickAddButton();
        Assert.assertTrue(createUserActions.verifyAddUserPageDisplayed());
    }

    @When("user enters user credential details {string} {string} {string} {string} {string} {string}")
    public void user_enters_user_credential_details(String role, String employeeName, String status,
                                                    String username, String password, String confirmPassword) {

        createUserActions.enterUserCredentialDetails(role, employeeName, status, username, password, confirmPassword);
    }

    @When("user clicks on Save button")
    public void user_clicks_on_save_button() {
        createUserActions.clickSaveButton();
    }

    @Then("user credential should be created successfully")
    public void user_credential_should_be_created_successfully() {
        Assert.assertTrue(createUserActions.verifySuccessMessageDisplayed());
    }
}