package com.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.pages.AdminLeaveManagement_LeaveApprovel_Pages;
import com.utilities.HelperClass;

import java.time.Duration;

public class AdminLeaveManagement_LeaveApprovel_Actions extends BaseActions {

	AdminLeaveManagement_LeaveApprovel_Pages pages;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	HelperClass helper = new HelperClass();
	private static final Logger log = LogManager.getLogger(AdminLeaveManagement_LeaveApprovel_Actions.class);
	public static List<String> collectedStatuses = new ArrayList<>();
	Actions actions = new Actions(helper.getDriver());
	JavascriptExecutor js;

	// Constructor
	public AdminLeaveManagement_LeaveApprovel_Actions() {
		super();
		pages = new AdminLeaveManagement_LeaveApprovel_Pages();
		js = (JavascriptExecutor) driver;
	}

	// click leaveMenu in Dash board page
	public void clickLeaveMenu() {
		pages.leaveMenu.click();
	}

	// click Leave List menu
	public void navigateToLeaveList() {
		helper.clickElement(pages.leaveListSubMenu);
	}

	// Set From date and To date
	public void setDateRange(String fromDate, String toDate) {
		setDateField(pages.fromDateInput, fromDate);
		setDateField(pages.toDateInput, toDate);
	}

	private void setDateField(WebElement dateInput, String dateValue) {

		dateInput.click();
		try {
			if (dateValue == null || dateValue.trim().isEmpty()) {
				log.info("Date field value is empty");
				return;
			}
			log.info("Entering date value");
			dateInput.click();
			dateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			dateInput.sendKeys(Keys.DELETE);
			dateInput.sendKeys(dateValue);
			dateInput.sendKeys(Keys.TAB);

		} catch (Exception e) {
			log.error("Failed to enter date in field", e);
			e.printStackTrace();
		}
	}

	// Select Show Leave with Status
	public void selectStatuss(String statusLabel) {
		log.info("Select which type leave type to filter");
		// Remove if any status is selected
		for (WebElement op : pages.selectedStatusOption) {
			pages.removeSelectedStatus.click();
		}
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", pages.statusDropdown);
		helper.clickElement(pages.statusDropdown);
		wait.until(ExpectedConditions.visibilityOfAllElements(pages.statusDropdownOptions));
		// Select the status type
		for (WebElement option : pages.statusDropdownOptions) {
			if (option.getText().equalsIgnoreCase(statusLabel)) {
				option.click();
				return;
			}
		}
	}

	// Select leave status
	public void selectStatus(String statusLabel) {
		log.info("Select leave status");
		helper.clickElement(pages.statusDropdown);
		wait.until(ExpectedConditions.visibilityOfAllElements(pages.statusDropdownOptions));

		for (WebElement option : pages.statusDropdownOptions) {
			String text = option.getText().trim();
			System.out.println(text);
			if (text.equalsIgnoreCase(statusLabel)) {
				option.click();
				break;
			}
		}
	}

	// de select the default select if any
	public void withoutSelectStatus(String selectStatus) {
		for (WebElement op : pages.selectedStatusOption) {
			pages.removeSelectedStatus.click();
		}
	}

	// Error message display if mandatory field leave blank
	public void errorMsgForMandatoryField() {
		Assert.assertEquals(pages.errorMsg.getText(), "Required");
	}

	public void selectLeaveType(String LeaveType) {
		pages.leaveType.click();
		for (WebElement option : pages.empOption) {
			if (option.getText().equalsIgnoreCase(LeaveType)) {
				option.click();
				break;
			}
		}
	}

	public void enterEmployeeName(String empName) {
		pages.employeeName.click();
		pages.employeeName.sendKeys(empName);
		wait.until(ExpectedConditions.invisibilityOf(pages.searching));
		wait.until(ExpectedConditions.visibilityOfAllElements(pages.empOption));
		for (WebElement option : pages.empOption) {
			if (option.getText().equalsIgnoreCase(empName)) {
				option.click();
				break;
			}
		}
	}

	public void clickSearch() {
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", pages.searchButton);
		HelperClass helper = new HelperClass();
		helper.clickElement(pages.searchButton);
	}

	public void clickApprove() {
		wait.until(ExpectedConditions.visibilityOf(pages.approveButton));
		js.executeScript("arguments[0].click();", pages.approveButton);
		log.info("Leave Approved");
	}

	public void clickReject() {
		wait.until(ExpectedConditions.visibilityOf(pages.rejectButton));
		js.executeScript("arguments[0].click();", pages.rejectButton);
		log.info("Leave Rejected");
	}

	public String getSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(pages.successToastMessage));
		return pages.successToastMessage.getText();
	}

	// Verify success message for both success and reject
	public void verifySuccessMessageContains(String expectedText) {
		String actual = getSuccessMessage();
		System.out.println(actual);
		Assert.assertTrue(actual.toLowerCase().contains(expectedText.toLowerCase()));
		log.info("Verified successfully");
	}

}