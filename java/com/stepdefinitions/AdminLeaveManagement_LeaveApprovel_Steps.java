package com.stepdefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.actions.AdminLeaveManagement_LeaveApprovel_Actions;
import com.actions.LoginActions;
import com.utilities.DP_Excel;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class AdminLeaveManagement_LeaveApprovel_Steps {

	LoginActions la;
	AdminLeaveManagement_LeaveApprovel_Actions leaveAction = new AdminLeaveManagement_LeaveApprovel_Actions();

	@Given("the User on the Home page")
	public void the_user_on_the_home_page() {
		la = new LoginActions();
		la.enterValidCredentials();
		la.clickLogin();
	}

	@Given("click on the Leave menu")
	public void click_on_the_leave_menu() {
		leaveAction.clickLeaveMenu();
	}

	@Given("User click on  leave List and navigates to the Leave List page")
	public void user_click_on_leave_list_and_navigates_to_the_leave_list_page() {
		leaveAction.navigateToLeaveList();
	}

	@When("User enters leave details")
	public void user_enters_leave_details() throws IOException {
		DP_Excel excel = new DP_Excel();
		Object[][] data = excel.getExcelData("src/test/resources/testdata/OrangeHRM_TestData.xlsx", "Leave Test data");

		String fromDate = data[0][0].toString();
		String toDate = data[0][1].toString();
		String status = data[0][2].toString();
		String leaveType = data[0][3].toString();
		String employeeName = data[0][4].toString();

		leaveAction.setDateRange(fromDate, toDate);
		leaveAction.selectStatus(status);
		leaveAction.selectLeaveType(leaveType);
		leaveAction.enterEmployeeName(employeeName);
	}

	@When("User clicks on the Approve button")
	public void user_clicks_on_the_approve_button() {
		leaveAction.clickSearch();
		leaveAction.clickApprove();
	}

	@Then("Leave request should be approved successfully")
	public void leave_request_should_be_approved_successfully() {
		leaveAction.verifySuccessMessageContains("Success");
	}

	@When("User clicks on the Reject button")
	public void user_clicks_on_the_reject_button() {
		leaveAction.clickReject();
	}

	@Then("Leave request should be rejected successfully")
	public void leave_request_should_be_rejected_successfully() {
		leaveAction.verifySuccessMessageContains("Success");
	}

	@When("User enters invalid data")
	public void user_enters_invalid_data(DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

		String fromDate = data.get(0).get("From date");
		String toDate = data.get(0).get("To date");
		String status = data.get(0).get("Leave status");
		String leaveType = data.get(0).get("Leave type");
		String employeeName = data.get(0).get("Employee name");

		leaveAction.setDateRange(fromDate, toDate);
		leaveAction.withoutSelectStatus(status);
		leaveAction.selectLeaveType(leaveType);
		leaveAction.enterEmployeeName(employeeName);

	}

	@Then("user get error message for blank field")
	public void user_get_error_message_for_blank_field() {
		leaveAction.errorMsgForMandatoryField();
	}

}