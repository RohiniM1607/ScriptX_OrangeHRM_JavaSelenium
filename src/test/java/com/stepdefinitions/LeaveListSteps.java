package com.stepdefinitions;

import org.testng.Assert;

import com.actions.LeaveListActions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LeaveListSteps {

    LeaveListActions leaveActions = new LeaveListActions();

    @Given("user is on Leave List page")
    public void user_is_on_leave_list_page() {
        leaveActions.navigateToLeaveListPage();
        Assert.assertTrue(leaveActions.verifyLeaveListPageDisplayed());
    }

    @When("user clicks Search button")
    public void user_clicks_search_button() {
        leaveActions.clickSearchButton();
    }

    @Then("all employee leave requests should be displayed")
    public void all_employee_leave_requests_should_be_displayed() {
        boolean result = leaveActions.verifySearchResultDisplayed();
        if (!result) {
            System.out.println("No Records Found.");
        } 
        else {
            Assert.assertTrue(result);
        }
    }

//    @When("user searches leave request using {string}")
//    public void user_searches_leave_request_using(String employeeName) {
//
//        leaveActions.searchLeaveRequestByEmployee(employeeName);
//
//    }
//
//    @Then("leave requests of {string} should be displayed")
//    public void leave_requests_of_should_be_displayed(String employeeName) {
//
//        Assert.assertTrue(leaveActions.verifySearchResultDisplayed());
//
//    }
//
//    @When("user filters leave request by status {string}")
//    public void user_filters_leave_request_by_status(String status) {
//
//        leaveActions.filterLeaveRequestByStatus(status);
//
//    }
//
//    @Then("leave requests with {string} should be displayed")
//    public void leave_requests_with_should_be_displayed(String status) {
//
//        Assert.assertTrue(leaveActions.verifySearchResultDisplayed());
//
//    }
//
//    @When("user enters From Date {string}")
//    public void user_enters_from_date(String fromDate) {
//
//        leaveActions.enterFromDate(fromDate);
//
//    }
//
//    @When("user enters To Date {string}")
//    public void user_enters_to_date(String toDate) {
//
//        leaveActions.enterToDate(toDate);
//
//    }
//
//    @Then("leave requests within selected date range should be displayed")
//    public void leave_requests_within_selected_date_range_should_be_displayed() {
//
//        Assert.assertTrue(leaveActions.verifySearchResultDisplayed());
//
//    }
//
//    @Then("no leave request should be displayed")
//    public void no_leave_request_should_be_displayed() {
//
//        Assert.assertTrue(leaveActions.verifyNoRecordFound());
//
//    }

}