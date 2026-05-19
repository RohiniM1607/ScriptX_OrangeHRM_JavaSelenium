package com.actions;

import com.pages.PIMPage;
import com.utilities.ExcelUtility;

public class PIMActions extends BaseActions {

	PIMPage pimPage = new PIMPage();

	ExcelUtility excel = new ExcelUtility("src/test/resources/testdata/PIMData.xlsx");

	public void navigateToAddEmployee() {

		pimPage.clickPIMMenu();

		pimPage.clickAddEmployee();
	}

	public void enterEmployeeDetails(String firstName, String lastName, String employeeId) {

		pimPage.enterFirstName(firstName);

		pimPage.enterLastName(lastName);

		pimPage.enterEmployeeId(employeeId);
	}

	public void clickSaveButton() {

		pimPage.clickSaveButton();
	}

	public boolean verifyEmployeeCreated() {

		return pimPage.isPersonalDetailsDisplayed();
	}

	public boolean verifyRequiredMessage() {

		return pimPage.isRequiredMessageDisplayed();
	}

	public void navigateToSearchEmployee() {

		pimPage.clickPIMMenu();
	}

	public void searchEmployeeByName() {

		String employeeName = excel.getData("SearchEmployee", 1, 0);

		pimPage.enterSearchEmployeeName(employeeName);

		pimPage.clickSearchButton();
	}

	public void searchEmployeeById() {

		String employeeId = excel.getData("SearchEmployee", 1, 1);

		pimPage.enterSearchEmployeeName(employeeId);

		pimPage.clickSearchButton();
	}

	public void searchInvalidEmployeeName() {

		String employeeName = excel.getData("SearchEmployee", 4, 0);

		pimPage.enterSearchEmployeeName(employeeName);

		pimPage.clickSearchButton();
	}

	public void searchInvalidEmployeeId() {

		String employeeId = excel.getData("SearchEmployee", 4, 1);

		pimPage.enterSearchEmployeeName(employeeId);

		pimPage.clickSearchButton();
	}

	public boolean verifySearchSuccess() {

		return pimPage.isSearchResultDisplayed();
	}

	public boolean verifyNoRecordsFound() {

		return pimPage.isNoRecordFoundDisplayed();
	}
}