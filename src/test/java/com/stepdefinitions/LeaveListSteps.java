package com.stepdefinitions;

import org.testng.Assert;

import com.actions.LeaveListActions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LeaveListSteps {

    LeaveListActions leaveListActions;

    @Given("user is on Leave List page")
    public void user_is_on_leave_list_page() {

        leaveListActions = new LeaveListActions();

        leaveListActions.navigateToLeaveListPage();

        Assert.assertTrue(
                leaveListActions.verifyLeaveListPageDisplayed(),
                "Leave List page is not displayed");
    }

    @When("user filters leave request by status {string}")
    public void user_filters_leave_request_by_status(String status) {

        leaveListActions.filterLeaveRequestByStatus(status);
    }

    @Then("leave requests with {string} should be displayed")
    public void leave_requests_with_should_be_displayed(String status) {

        Assert.assertTrue(
                leaveListActions.verifySearchResultDisplayed(),
                "Leave request results are not displayed for status : "
                        + status);
    }
    
    @When("user filters leave request by date range")
    public void user_filters_leave_request_by_date_range() {
        leaveListActions.filterLeaveRequestByDateRange();
    }

    @Then("leave requests within selected date range should be displayed")
    public void leave_requests_within_selected_date_range_should_be_displayed() {

        Assert.assertTrue(leaveListActions.verifySearchResultDisplayed(), "Leave requests are not displayed");
    }
}