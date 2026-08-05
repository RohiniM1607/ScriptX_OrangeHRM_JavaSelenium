package com.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PIMPage extends BasePage {

	@FindBy(xpath = "//span[normalize-space()='PIM']")
	WebElement pimMenu;

	@FindBy(xpath = "//a[normalize-space()='Add Employee']")
	WebElement addEmployeeMenu;

	@FindBy(name = "firstName")
	WebElement firstNameTxt;

	@FindBy(name = "lastName")
	WebElement lastNameTxt;

	@FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
	WebElement employeeIdTxt;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveBtn;

	@FindBy(xpath = "//h6[text()='Personal Details']")
	WebElement personalDetailsHeader;

	@FindBy(xpath = "//span[text()='Required']")
	List<WebElement> requiredMsg;

	@FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
	WebElement employeeNameSearchTxt;

	@FindBy(xpath = "(//input[contains(@class,'oxd-input')])[2]")
	WebElement employeeIdSearchTxt;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchBtn;

	@FindBy(xpath = "//div[@class='oxd-table-body']")
	WebElement searchResultTable;

	@FindBy(xpath = "//*[text()='No Records Found']")
	WebElement noRecordsFoundTxt;

	public void clickByJS(WebElement element) {

		helper.waitForElement(element);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		js.executeScript("arguments[0].click();", element);
	}

	public void enterText(WebElement element, String value) {

		helper.waitForElement(element);

		element.clear();

		element.sendKeys(value);
	}

	public void clickPIMMenu() {

		helper.waitForElement(pimMenu);

		clickByJS(pimMenu);
	}

	public void clickAddEmployee() {

		helper.waitForElement(addEmployeeMenu);

		clickByJS(addEmployeeMenu);
	}

	public void enterFirstName(String firstName) {

		enterText(firstNameTxt, firstName);
	}

	public void enterLastName(String lastName) {

		enterText(lastNameTxt, lastName);
	}

	public void enterEmployeeId(String employeeId) {

		enterText(employeeIdTxt, employeeId);
	}

	public void clickSaveButton() {

		clickByJS(saveBtn);
	}

	public boolean isPersonalDetailsDisplayed() {

		try {

			helper.waitForElement(personalDetailsHeader);

			return personalDetailsHeader.isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	public boolean isRequiredMessageDisplayed() {

		return requiredMsg.size() > 0;
	}

	public void enterSearchEmployeeName(String employeeName) {

		enterText(employeeNameSearchTxt, employeeName);
	}

	public void enterSearchEmployeeId(String employeeId) {

		enterText(employeeIdSearchTxt, employeeId);
	}

	public void clickSearchButton() {

		clickByJS(searchBtn);
	}

	public boolean isSearchResultDisplayed() {

		try {

			helper.waitForElement(searchResultTable);

			return searchResultTable.isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	public boolean isNoRecordFoundDisplayed() {

		try {

			helper.waitForElement(noRecordsFoundTxt);

			return noRecordsFoundTxt.isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}
}