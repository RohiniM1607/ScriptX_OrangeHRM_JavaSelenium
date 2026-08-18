package com.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import com.actions.EmployeeLeaveMyEntitlement_Actions;

public class EmployeeLeaveMyEntitlement_Steps {

    EmployeeLeaveMyEntitlement_Actions employeeLeaveMyEntitlementActions = new EmployeeLeaveMyEntitlement_Actions();

    @When("user navigates to Leave Entitlements page")
    public void user_navigates_to_leave_entitlements_page() {
        employeeLeaveMyEntitlementActions.navigateToLeaveEntitlementsPage();
    }

    @When("user selects leave type {string}")
    public void user_selects_leave_type(String leaveType) {
        employeeLeaveMyEntitlementActions.selectLeaveType(leaveType);
    }

    @When("user selects leave period {string}")
    public void user_selects_leave_period(String leavePeriod) {
        employeeLeaveMyEntitlementActions.selectLeavePeriod(leavePeriod);
    }

    @When("user clicks search entitlements button")
    public void user_clicks_search_entitlements_button() {
        employeeLeaveMyEntitlementActions.clickSearchButton();
    }

    @Then("entitlement records should be displayed")
    public void entitlement_records_should_be_displayed() {
        Assert.assertTrue(employeeLeaveMyEntitlementActions.isRecordsFoundDisplayed(), "Records Found text is not displayed");
    }

    @Then("No Records Found message should be displayed")
    public void no_records_found_message_should_be_displayed() {
        Assert.assertTrue(employeeLeaveMyEntitlementActions.isNoRecordsFoundDisplayed(), "No Records Found message is not displayed");
    }

    @Then("Total Days text should be visible")
    public void total_days_text_should_be_visible() {
        Assert.assertTrue(employeeLeaveMyEntitlementActions.isTotalDaysDisplayed(), "Total Days text is not displayed");
        System.out.println("Displayed Total Days text: " + employeeLeaveMyEntitlementActions.getTotalDaysText());
    }
}