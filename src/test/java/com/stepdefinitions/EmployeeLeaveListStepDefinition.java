
package com.stepdefinitions;

import com.actions.LeaveListEmployeeActions;
import com.utilities.HelperClass;
import com.actions.LoginActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class EmployeeLeaveListStepDefinition{
	LoginActions loginActions = new LoginActions();
	HelperClass helper = new HelperClass();
    LeaveListEmployeeActions leaveListActions = new LeaveListEmployeeActions(helper.getDriver());

    String actualStatus;
    String expectedStatus;
    String balanceText;
   

    @When("user navigates to My Leave page")
    public void user_navigates_to_my_leave_page() {
        leaveListActions.navigateToMyLeavePage();
    }

    @And("user searches leave with the following details")
    public void user_searches_leave_with_the_following_details(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = rows.get(0);

        String leaveType = row.get("leaveType");
        expectedStatus = row.get("expectedStatus");

        leaveListActions.selectLeaveType(leaveType);
        leaveListActions.clickSearch();
        actualStatus = leaveListActions.getStatusForLeaveType(leaveType);
    }

    @Then("leave status should match expected status")
    public void leave_status_should_match_expected_status() {
        Assert.assertEquals(
                "Expected status '" + expectedStatus + "' but got '" + actualStatus + "'",
                expectedStatus, actualStatus);
    }

    @When("user searches leave balance with the following details")
    public void user_searches_leave_balance_with_the_following_details(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = rows.get(0);

        String leaveType = row.get("leaveType");

        leaveListActions.navigateToMyLeavePage();
        balanceText = leaveListActions.getLeaveBalanceForLeaveType(leaveType);
    }

    @Then("remaining leave balance should be greater than 0")
    public void remaining_leave_balance_should_be_greater_than_0() {
        try {
            double balance = Double.parseDouble(balanceText);
            Assert.assertTrue(
                    "No remaining leave balance. Remaining Leave Balance: " + balanceText,
                    balance > 0);
        } catch (NumberFormatException e) {
            Assert.fail("Unexpected leave balance value found: '" + balanceText + "'");
        }
    }
  


}