package com.stepdefinitions;

import java.io.IOException;

import org.testng.Assert;

import com.actions.EmployeeEntitlementActions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeEntitlementSteps {

    EmployeeEntitlementActions employeeEntitlementActions;

    @Given("user is on OrangeHRM Leave Entitlements page")
    public void user_is_on_orange_hrm_leave_entitlements_page() {
        employeeEntitlementActions = new EmployeeEntitlementActions();
        employeeEntitlementActions.navigateToEmployeeEntitlementsPage();

        Assert.assertTrue(employeeEntitlementActions.verifyEmployeeEntitlementsPageDisplayed(),"Employee Entitlements page is not displayed");
    }

    @When("user searches employee entitlement")
    public void user_searches_employee_entitlement(DataTable dataTable) throws IOException {

        employeeEntitlementActions.searchEmployeeEntitlement(dataTable);
    }

    @Then("employee entitlement search result should be displayed")
    public void employee_entitlement_search_result_should_be_displayed() {
        Assert.assertTrue(employeeEntitlementActions.verifySearchResultDisplayed(), "Employee entitlement search result is not displayed");
    }

    @When("user searches entitlement with invalid employee name {string}")
    public void user_searches_entitlement_with_invalid_employee_name(String employeeName) {

        employeeEntitlementActions.searchWithInvalidEmployeeName(employeeName);
    }

    @Then("invalid employee name validation should be displayed")
    public void invalid_employee_name_validation_should_be_displayed() {
        Assert.assertTrue(employeeEntitlementActions.verifyInvalidEmployeeValidationDisplayed(), "Invalid employee name validation message is not displayed");
    }

    @When("user searches entitlement without employee name")
    public void user_searches_entitlement_without_employee_name() throws IOException {
        employeeEntitlementActions.searchWithoutEmployeeName();
    }

    @Then("required employee name validation should be displayed")
    public void required_employee_name_validation_should_be_displayed() {
        Assert.assertTrue(employeeEntitlementActions.verifyRequiredEmployeeNameValidationDisplayed(),"Required employee name validation message is not displayed");
    }
}