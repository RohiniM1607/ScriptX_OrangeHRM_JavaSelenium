package com.stepdefinitions;

import com.actions.AdminLeaveManagement_LeaveApprovel_Actions;
import com.actions.Employee_ApplyLeave_Actions;
import com.actions.LoginActions;
import com.pages.Employee_ApplyLeave_Page;
import com.utilities.HelperClass;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Employee_ApplyLeave_Definition {

    LoginActions loginActions = new LoginActions();
    Employee_ApplyLeave_Actions applyLeaveActions = new Employee_ApplyLeave_Actions();
    //Add_leave_entitlement_action leave_entitlement = new Add_leave_entitlement_action();
    AdminLeaveManagement_LeaveApprovel_Actions leave_approvel=new AdminLeaveManagement_LeaveApprovel_Actions();
    Employee_ApplyLeave_Page page = new Employee_ApplyLeave_Page();
    HelperClass helper = new HelperClass();
    
    @Given("Employee login with valid credentials")
    public void employee_login_with_valid_credentials() {
        loginActions.enterEmployeeCredentials();
        loginActions.clickLogin();
    }

    @Given("Employee is on the dashboard page")
    public void employee_is_on_the_dashboard_page() {
        System.out.println("Employee is on the dashboard page.");
    }

    @When("the user navigates to Apply Leave and selects {string} leave type")
    public void the_user_navigates_to_apply_leave_and_selects_leave_type(String leaveType) throws InterruptedException {
    	applyLeaveActions.navigateToApplyLeave();
    	applyLeaveActions.leaveType_field(leaveType); 
    	// Actions actions = new Actions(helper.getDriver());
  	    //actions.sendKeys(Keys.TAB).perform();
    	}

    // ---- New: apply leave without selecting a leave type ----
    @When("the user navigates to Apply Leave without selecting leave type")
    public void the_user_navigates_to_apply_leave_without_selecting_leave_type() throws InterruptedException {
        applyLeaveActions.navigateToApplyLeaveWithoutSelectingType();
    }

    @Then("the required field error message should display")
    public void the_required_field_error_message_should_display() {
        applyLeaveActions.verifyRequiredFieldErrorMessage();
    }
    
    @And("selects {string} {string}")
    public void selects(String fromDate, String toDate) {
    	applyLeaveActions.setDateRange(fromDate, toDate) ;
    }
    
    @And("click on save button")
    public void click_on_save_button() {
        applyLeaveActions.clickSave();
    }
    
    @Then("the success message should display")
    public void the_success_message_should_display() {
        applyLeaveActions.confirmation_message();
    }

    // ---- New: cancel an applied leave from My Leave list ----
    @When("the user navigates to My Leave list")
    public void the_user_navigates_to_my_leave_list() {
        applyLeaveActions.navigateToMyLeaveList();
    }

    @And("cancels the applied leave for {string}")
    public void cancels_the_applied_leave_for(String leaveType) {
        applyLeaveActions.cancelAppliedLeave(leaveType);
    }

    @Then("the leave cancelled message should display")
    public void the_leave_cancelled_message_should_display() {
        applyLeaveActions.confirmCancelMessage();
    }
}