package com.stepdefinitions;

import org.testng.Assert;
import com.actions.Employee_ApplyLeave_Actions;
import com.actions.LoginActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Employee_ApplyLeave_Definition {

    LoginActions loginActions = new LoginActions();
    Employee_ApplyLeave_Actions applyLeaveActions = new Employee_ApplyLeave_Actions();

    @Given("Employee login with valid credentials")
    public void employee_login_with_valid_credentials() {
        loginActions.enterValidCredentials();
        loginActions.clickLogin();
    }

    @Given("Employee is on the dashboard page")
    public void employee_is_on_the_dashboard_page() {
        System.out.println("Employee is on the dashboard page.");
    }

    @When("the user navigates to Apply Leave and selects {string} leave type")
    public void the_user_navigates_to_apply_leave_and_selects_leave_type(String leaveType) {
      
        applyLeaveActions.selectLeaveType(leaveType);
    }
    

    @Then("correct leave balance should display for {string}")
    public void correct_leave_balance_should_display_for(String leaveType) {
        String balanceText = applyLeaveActions.getLeaveBalanceText();
        Assert.assertNotNull(balanceText, "Leave balance is not displayed!");
        Assert.assertFalse(balanceText.isEmpty(), "Leave balance is empty!");
        Assert.assertTrue(balanceText.contains("Day(s)"), "Leave balance format incorrect. Actual: " + balanceText);
        System.out.println("Leave Balance for " + leaveType + ": " + balanceText);
    }
}