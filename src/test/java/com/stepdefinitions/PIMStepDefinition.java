package com.stepdefinitions;

import org.testng.Assert;

import com.actions.LoginActions;
import com.actions.PIMActions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PIMStepDefinition {

	LoginActions login = new LoginActions();
	PIMActions pim = new PIMActions();

	@Given("admin is logged into OrangeHRM")
	public void admin_is_logged_into_orangehrm() {

		login.enterValidCredentials();
		login.clickLogin();

		pim.navigateToAddEmployee();
	}

	@When("admin enters employee details {string} {string} {string}")
	public void admin_enters_employee_details(String firstName, String lastName, String employeeId) {

		pim.enterEmployeeDetails(firstName, lastName, employeeId);
	}

	@When("admin clicks save button")
	public void admin_clicks_save_button() {

		pim.clickSaveButton();
	}

	@Then("employee result should be {string}")
	public void employee_result_should_be(String result) {

		if (result.equalsIgnoreCase("success")) {

			Assert.assertTrue(pim.verifyEmployeeCreated(), "Employee creation failed");
		}

		else if (result.equalsIgnoreCase("required")) {

			Assert.assertTrue(pim.verifyRequiredMessage(), "Required validation message not displayed");
		}
	}
}