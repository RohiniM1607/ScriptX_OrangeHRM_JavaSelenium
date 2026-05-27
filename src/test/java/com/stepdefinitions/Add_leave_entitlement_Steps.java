package com.stepdefinitions;

import com.actions.Add_leave_entitlement_action;
import com.actions.LoginActions;
import com.utilities.ConfigReader;
import io.cucumber.java.en.*;

public class Add_leave_entitlement_Steps {

	LoginActions la;
	Add_leave_entitlement_action aa = new Add_leave_entitlement_action();
	ConfigReader testData = new ConfigReader("testData.properties");

	@Given("Admin is on the Leave page")
	public void admin_is_on_the_leave_page() {
		la = new LoginActions();
		la.enterValidCredentials();
		la.clickLogin();
	}

	@Given("Admin clicks on Entitlements and Admin selects Add Entitlements")
	public void admin_clicks_on_entitlements_and_admin_selects_add_entitlements() {
		aa.leavePage();
		aa.addEntitlement();
	}

	@When("Admin enters valid employee leave entitlement details")
	public void admin_enters_valid_employee_leave_entitlement_details() {
		String expectedType = testData.getData("expectedType");
		String entitlement = testData.getData("entitlement");
		String employeeName = testData.getData("employeeName");
		aa.employeeNameField(employeeName);
		aa.leaveType_field(expectedType);
		aa.entitlement_field(entitlement);
	}

	@When("clicks on the Save button")
	public void clicks_on_the_save_button() {
		aa.save();
	}

	@Then("the Updating Entitlement pop-up should be displayed and  leave entitlement should be added successfully")
	public void the_updating_entitlement_pop_up_should_be_displayed_and_leave_entitlement_should_be_added_successfully() {
		aa.confirmation_message();
	}

	@When("Admin enters {string}, {string}, {string} in input field")
	public void admin_enters_in_input_field(String EmployeeName, String LeaveType, String Entitlement) {
		// aa.Name_field(EmployeeName);
		aa.leaveType_field(LeaveType);
		aa.entitlement_field(Entitlement);
	}

	@Then("the error message should be displayed")
	public void the_error_message_should_be_displayed() {
		aa.nameField_Error_message();
	}
}