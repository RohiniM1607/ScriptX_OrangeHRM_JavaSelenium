package com.stepdefinitions;

import com.actions.AdminLeaveManagement_LeaveApprovel_Actions;
import com.actions.LoginActions;

import io.cucumber.java.en.*;

public class AdminLeaveManagement_LeaveApprovel_Steps {

	LoginActions la;
	AdminLeaveManagement_LeaveApprovel_Actions actions = new AdminLeaveManagement_LeaveApprovel_Actions();

	@Given("the User on the Home page")
	public void the_user_on_the_home_page() {
		la = new LoginActions();
		la.enterValidCredentials();
		la.clickLogin();
	}

	@Given("click on the Leave menu")
	public void click_on_the_leave_menu() {
		actions.clickLeaveMenu();

	}

	@Given("User navigates to the Leave List page")
	public void user_navigates_to_the_leave_list_page() {
		actions.navigateToLeaveList();

	}

	@When("User set the date from {string} to {string}")
	public void user_set_the_date_from_to(String fromDate, String toDate) {
		actions.setDateRange(fromDate, toDate);
	}

	@And("selects a pending leave request and click on search button")
	public void selects_a_pending_leave_request_and_click_on_search_button() {
		//actions.selectStatus("Pending Approval");
		actions.clickSearch();
		actions.clickFirstLeaveRow();
	}

	@And("User clicks on the Approve button")
	public void user_clicks_on_the_approve_button() {
		actions.clickApprove();
	}

	@Then("Leave request should be approved successfully")
	public void leave_request_should_be_approved_successfully() {
		actions.verifySuccessMessageContains("Successfully");

	}

	@And("selects a pending leave request")
	public void selects_a_pending_leave_request() {
		//actions.selectStatus("Pending Approval");
		actions.clickSearch();
		actions.clickFirstLeaveRow();
	}

	@And("User clicks on the Reject button")
	public void user_clicks_on_the_reject_button() {
		actions.clickReject();
	}

	@Then("Leave request should be rejected successfully")
	public void leave_request_should_be_rejected_successfully() {
		actions.verifySuccessMessageContains("Successfully");
	}

	@And("selects a Scheduled leave status")
	public void selects_a_scheduled_leave_status() {
		actions.selectStatus("Scheduled");
		actions.clickSearch();
	}

	@When("User views the Leave List")
	public void user_views_the_leave_list() {
		actions.collectLeaveStatuses();
	}

	@Then("The leave status should be displayed as {string}")
	public void the_leave_status_should_be_displayed_as(String expectedStatus) {
		actions.verifyAllStatusesMatch(expectedStatus);
	}

	@And("selects a Rejected leave status")
	public void selects_a_rejected_leave_status(){
		actions.selectStatus("Rejected");
		actions.clickSearch();
	}

}
