package com.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;

import com.actions.CreateUserCredentialsActions;
import com.utilities.DP_Excel;

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
    
    @When("user enters user credential details with duplicate username {string} {string} {string} {string} {string} {string}")
    public void user_enters_user_credential_details_with_duplicate_username(String role, String employeeName, String status,
                                                    String username, String password, String confirmPassword) {

        createUserActions.duplicateUserCredentialDetails(role, employeeName, status, username, password, confirmPassword);
    }
    @When("user clicks on Save button without entering mandatory fields")
    public void user_clicks_on_save_button_without_entering_mandatory_fields() throws IOException {

        DP_Excel excel = new DP_Excel();

        Object[][] data = excel.getExcelData("src/test/resources/TestData/CreateUserCredential.xlsx", "Sheet1");

        String role = getCellValue(data[0][0]);
        String employeeName = getCellValue(data[0][1]);
        String status = getCellValue(data[0][2]);
        String username = getCellValue(data[0][3]);
        String password = getCellValue(data[0][4]);
        String confirmPassword = getCellValue(data[0][5]);

        createUserActions.enterUserCredentialDetails(role, employeeName, status, username, password, confirmPassword);
        createUserActions.clickSaveButton();
    }
    
    @When("user enters user credential details with password mismatch {string} {string} {string} {string} {string} {string}")
    public void user_enters_user_credential_details_with_password_mismatch(String role, String employeeName,
            String status, String username, String password, String confirmPassword) {

        createUserActions.passwordMismatchUserCredentialDetails(role, employeeName, status,
                username, password, confirmPassword);
    }
 
    @When("user clicks on Save button")
    public void user_clicks_on_save_button() {
        createUserActions.clickSaveButton();
    }

    @Then("user credential should be created successfully")
    public void user_credential_should_be_created_successfully() {
        Assert.assertTrue(createUserActions.verifySuccessMessageDisplayed());
    }

    @Then("required validation message should be displayed for mandatory fields")
    public void required_validation_message_should_be_displayed_for_mandatory_fields() {
        Assert.assertTrue(createUserActions.verifyRequiredValidationMessageDisplayed());
    }

    public String getCellValue(Object value) {
        if (value == null) {
            return "";
        }
        return value.toString().trim();
    }
    
    @Then("required validation message should be displayed for duplicate username")
    public void required_validation_message_should_be_displayed_for_duplicate_username() {
    	createUserActions.verifyDuplicateUsernameValidationMessage();
    }
    @Then("required validation message should be displayed for password mismatch")
    public void required_validation_message_should_be_displayed_for_password_mismatch() {
        createUserActions.verifyPasswordMismatchValidationMessage();
    }
}