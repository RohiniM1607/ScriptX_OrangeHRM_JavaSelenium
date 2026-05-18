package com.stepdefinitions;

import org.testng.Assert;

import com.actions.LoginActions;
import com.hooks.Hooks;

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

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
		action.clickLogin();
	}

	@Then("user should be navigated to dashboard page")
	public void user_should_be_navigated_to_dashboard_page() {
		Assert.assertTrue(Hooks.driver.getCurrentUrl().contains("dashboard"));
	}
}