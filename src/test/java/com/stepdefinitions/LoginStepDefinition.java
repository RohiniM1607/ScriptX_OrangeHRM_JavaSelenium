package com.stepdefinitions;

import org.testng.Assert;

import com.actions.LoginActions;
import com.utilities.HelperClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinition {

	LoginActions action;

	@Given("user is on OrangeHRM login page")
	public void user_is_on_orangehrm_login_page() {
		action = new LoginActions();
	}

	@When("user enters valid username and password")
	public void user_enters_valid_username_and_password() {
		action.enterValidCredentials();
	}

	@When("user enters invalid username and password")
	public void user_enters_invalid_username_and_password() {
		action.enterInvalidCredentials();
	}

	@When("user enters empty username and password")
	public void user_enters_empty_username_and_password() {
		action.enterEmptyCredentials();
	}

	@When("user enters valid username and invalid password")
	public void user_enters_valid_username_and_invalid_password() {
		action.enterValidUsernameInvalidPassword();
	}

	@When("user enters invalid username and valid password")
	public void user_enters_invalid_username_and_valid_password() {
		action.enterInvalidUsernameValidPassword();
	}

	@When("user enters valid employee_username and employee_password")
	public void user_enters_valid_employee_username_and_employee_password() {
		action.enterEmployeeCredentials();
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
		action.clickLogin();
	}

	@Then("user should be navigated to dashboard page")
	public void user_should_be_navigated_to_dashboard_page() {
		Assert.assertTrue(HelperClass.driver.get().getCurrentUrl().contains("dashboard"));
	}

	@Then("employee should be navigated to dashboard page")
	public void employee_should_be_navigated_to_dashboard_page() {
		Assert.assertTrue(HelperClass.driver.get().getCurrentUrl().contains("dashboard"));
	}

	@Then("user should see invalid credentials error message")
	public void user_should_see_invalid_credentials_error_message() {

		Assert.assertEquals(action.getErrorMessage(), "Invalid credentials");
	}

	@Then("user should remain on login page")
	public void user_should_remain_on_login_page() {

		Assert.assertTrue(HelperClass.driver.get().getCurrentUrl().contains("auth/login"));
	}

	@Then("user should see required field validation message")
	public void user_should_see_required_field_validation_message() {

		Assert.assertTrue(HelperClass.driver.get().getPageSource().contains("Required"));
	}

	@Then("user should be logged out successfully")
	public void user_should_be_logged_out_successfully() {

		action.logout();

		Assert.assertTrue(HelperClass.driver.get().getCurrentUrl().contains("auth/login"));
	}
}