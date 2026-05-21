package com.stepdefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.actions.LoginActions;
import com.actions.PIMActions;
import com.utilities.DP_Excel;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PIMStepDefinition {

	LoginActions login = new LoginActions();

	PIMActions pim = new PIMActions();

	DP_Excel excel = new DP_Excel();

	@Given("admin is logged into OrangeHRM")
	public void admin_is_logged_into_orangehrm() {

		login.enterValidCredentials();

		login.clickLogin();

		pim.navigateToAddEmployee();
	}

	@When("admin creates employees with following data")
	public void admin_creates_employees_with_following_data(DataTable dataTable) {

		for (Map<String, String> data : dataTable.asMaps()) {

			String firstName = data.get("firstName");
			String lastName = data.get("lastName");
			String employeeId = data.get("employeeId");
			String result = data.get("result");

			pim.navigateToAddEmployee();

			if (firstName == null)
				firstName = "";

			if (lastName == null)
				lastName = "";

			if (employeeId == null)
				employeeId = "";

			pim.enterEmployeeDetails(firstName, lastName, employeeId);

			pim.clickSaveButton();

			if (result.equalsIgnoreCase("success")) {

				Assert.assertTrue(pim.verifyEmployeeCreated());

			} else {

				Assert.assertTrue(pim.verifyRequiredMessage());
			}
		}
	}

	@Given("admin is logged into OrangeHRM search page")
	public void admin_is_logged_into_orangehrm_search_page() {

		login.enterValidCredentials();

		login.clickLogin();

		pim.navigateToSearchEmployee();
	}

	@When("admin searches employee by employee name")
	public void admin_searches_employee_by_employee_name() throws IOException {

		Object[][] data = excel.getExcelData("src/test/resources/testdata/PIMData.xlsx", "SearchEmployee");


			String employeeName = data[0][0].toString();

			pim.searchEmployeeByName(employeeName);
		
	}

	@When("admin searches employee by employee ID")
	public void admin_searches_employee_by_employee_id() throws IOException {

		Object[][] data = excel.getExcelData("src/test/resources/testdata/PIMData.xlsx", "SearchEmployee");

		

			String employeeId = data[1][1].toString();

			pim.searchEmployeeById(employeeId);
		
	}

	@When("admin searches employee with invalid employee name")
	public void admin_searches_employee_with_invalid_employee_name() throws IOException {

		Object[][] data = excel.getExcelData("src/test/resources/testdata/PIMData.xlsx", "SearchEmployee");

		

			String employeeName = data[2][0].toString();

			pim.searchInvalidEmployeeName(employeeName);
		
	}

	@When("admin searches employee with invalid employee ID")
	public void admin_searches_employee_with_invalid_employee_id() throws IOException {

		Object[][] data = excel.getExcelData("src/test/resources/testdata/PIMData.xlsx", "SearchEmployee");

		

			String employeeId = data[3][1].toString();

			pim.searchInvalidEmployeeId(employeeId);
		
	}

	@Then("employee search result should be displayed")
	public void employee_search_result_should_be_displayed() {

		Assert.assertTrue(pim.verifySearchSuccess(), "Employee search failed");
	}

	@Then("no employee records should be displayed")
	public void no_employee_records_should_be_displayed() {

		Assert.assertTrue(pim.verifyNoRecordsFound(), "Records found unexpectedly");
	}
}