package com.actions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.pages.Employee_ApplyLeave_Page;
import com.utilities.HelperClass;

public class Employee_ApplyLeave_Actions extends BaseActions {

	Employee_ApplyLeave_Page page = new Employee_ApplyLeave_Page();

	HelperClass helper = new HelperClass();

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

	// Stores the from-date used for the current leave application.
	// Used later to identify the exact leave row during cancellation.
	private String appliedFromDate;

	// ============================================================
	// NAVIGATE TO APPLY LEAVE
	// ============================================================

	public void navigateToApplyLeave() throws InterruptedException {

		helper.waitForElement(page.leaveMenu);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", page.leaveMenu);

		System.out.println("Clicked 'Leave' in sidebar.");

		helper.waitForElement(page.applySubMenu);

		helper.clickElement(page.applySubMenu);

		System.out.println("Clicked 'Apply' sub-menu.");

		helper.waitForElement(page.leave_type);

		// Wait until OrangeHRM loading overlay disappears.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

		System.out.println("Apply Leave page loaded successfully.");
	}

	// ============================================================
	// NAVIGATE TO APPLY LEAVE WITHOUT LEAVE TYPE
	// ============================================================

	public void navigateToApplyLeaveWithoutSelectingType() throws InterruptedException {

		helper.waitForElement(page.leaveMenu);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", page.leaveMenu);

		System.out.println("Clicked 'Leave' in sidebar.");

		helper.waitForElement(page.applySubMenu);

		helper.clickElement(page.applySubMenu);

		System.out.println("Clicked 'Apply' sub-menu.");

		helper.waitForElement(page.leave_type);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

		System.out.println("Apply Leave page loaded successfully. " + "Leave Type intentionally left unselected.");
	}

	// ============================================================
	// SELECT LEAVE TYPE
	// ============================================================

	public void selectLeaveType(String leaveType) {

		helper.clickElement(page.leave_type);

		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElements(page.leaveTypeOptions));

		System.out.println("Total leave type options found: " + options.size());

		Actions actions = new Actions(helper.getDriver());

		actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(1));

		actions.sendKeys(Keys.ENTER).perform();

		WebElement leaveOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[contains(@class,'oxd-select-dropdown')]" + "//span[normalize-space()='" + leaveType + "']")));

		leaveOption.click();

		System.out.println("Leave type selected: " + leaveType);
	}

	// ============================================================
	// VERIFY SUCCESS MESSAGE
	// ============================================================

	public void confirmation_message() {

		try {

			wait.until(ExpectedConditions.visibilityOf(page.confirm));

			Assert.assertTrue("Expected Successfully Saved toast to be displayed", page.confirm.isDisplayed());

			System.out.println("Successfully Saved confirmation message displayed.");

		} catch (org.openqa.selenium.TimeoutException e) {

			// Check whether another OrangeHRM toast appeared.
			List<WebElement> anyToast = driver.findElements(By.cssSelector("div.oxd-toast"));

			if (!anyToast.isEmpty()) {

				String toastText = anyToast.get(0).getText();

				Assert.fail("Expected the 'Successfully Saved' toast, " + "but a different toast appeared instead: \""
						+ toastText + "\". OrangeHRM may have rejected " + "the leave request.");
			}

			// No toast appeared.
			String currentUrl = driver.getCurrentUrl();

			String pageTitle = driver.getTitle();

			List<WebElement> inlineErrors = driver
					.findElements(By.xpath("//span[contains(@class," + "'oxd-input-field-error-message')]"));

			String inlineErrorText = inlineErrors.isEmpty() ? "none" : inlineErrors.get(0).getText();

			boolean loaderStillVisible = !driver.findElements(By.cssSelector("div.oxd-form-loader")).isEmpty();

			System.out.println("---- No toast appeared - diagnostic dump ----");

			System.out.println("Current URL: " + currentUrl);

			System.out.println("Page title: " + pageTitle);

			System.out.println("Inline field error present: " + inlineErrorText);

			System.out.println("Loader still in DOM: " + loaderStillVisible);

			System.out.println("---------------------------------------------");

			Assert.fail("No confirmation toast appeared after clicking Save " + "within the configured timeout. "
					+ "URL=" + currentUrl + ", inlineFieldError=" + inlineErrorText + ", loaderStillPresent="
					+ loaderStillVisible);
		}
	}

	// ============================================================
	// VERIFY REQUIRED FIELD ERROR
	// ============================================================

	public void verifyRequiredFieldErrorMessage() {

		wait.until(ExpectedConditions.visibilityOf(page.leaveTypeRequiredError));

		Assert.assertTrue("Expected Leave Type required-field error " + "to be displayed",
				page.leaveTypeRequiredError.isDisplayed());

		Assert.assertEquals("Required", page.leaveTypeRequiredError.getText().trim());

		System.out.println("Required field validation message displayed " + "for Leave Type.");
	}

	// ============================================================
	// RESOLVE AUTO DATE
	// ============================================================

	public static String resolveDate(String rawDate) {

		if (rawDate == null || !rawDate.equalsIgnoreCase("AUTO")) {

			return rawDate;
		}

		LocalDate today = LocalDate.now();

		LocalDate endOfYear = LocalDate.of(today.getYear(), 12, 31);

		long daysRemaining = java.time.temporal.ChronoUnit.DAYS.between(today, endOfYear);

		long range = Math.max(daysRemaining, 1);

		long offset = 1 + ((System.currentTimeMillis() / 1000L) % range);

		LocalDate date = today.plusDays(offset);

		// Skip weekends.
		DayOfWeek dow = date.getDayOfWeek();

		if (dow == DayOfWeek.SATURDAY) {

			date = date.plusDays(2);

		} else if (dow == DayOfWeek.SUNDAY) {

			date = date.plusDays(1);
		}

		// Make sure date does not cross year.
		if (date.isAfter(endOfYear)) {

			date = endOfYear;

			while (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {

				date = date.minusDays(1);
			}
		}

		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	// ============================================================
	// SET FROM DATE AND TO DATE
	// ============================================================

	public void setDateRange(String fromDate, String toDate) {

		// If both dates are AUTO,
		// use the same generated date.
		if ("AUTO".equalsIgnoreCase(fromDate) && "AUTO".equalsIgnoreCase(toDate)) {

			fromDate = resolveDate(fromDate);

			toDate = fromDate;

		} else {

			fromDate = resolveDate(fromDate);

			toDate = resolveDate(toDate);
		}

		// Save the date for cancellation.
		this.appliedFromDate = fromDate;

		// Wait for From Date field.
		wait.until(ExpectedConditions.visibilityOf(page.fromDateInput));

		// FROM DATE
		page.fromDateInput.click();

		page.fromDateInput.sendKeys(Keys.CONTROL + "a");

		page.fromDateInput.sendKeys(Keys.DELETE);

		page.fromDateInput.sendKeys(fromDate);

		// TO DATE
		page.toDateInput.click();

		page.toDateInput.sendKeys(Keys.CONTROL + "a");

		page.toDateInput.sendKeys(Keys.DELETE);

		page.toDateInput.sendKeys(toDate);

		page.toDateInput.sendKeys(Keys.TAB);

		System.out.println("From Date: " + fromDate + " | To Date: " + toDate);
	}

	// ============================================================
	// SELECT LEAVE TYPE FROM FIELD
	// ============================================================

	public void leaveType_field(String expectedType) {

		// Wait for loader to disappear.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

		helper.clickElement(page.leave_type);

		wait.until(ExpectedConditions.visibilityOf(page.empOption));

		for (WebElement option : page.leaveTypeOptions) {

			String actualText = option.getText();

			if (actualText.equalsIgnoreCase(expectedType)) {

				option.click();

				System.out.println("Leave type selected: " + expectedType);

				break;
			}
		}

		// Selecting leave type triggers an async call.
		// Wait until date field becomes visible.
		wait.until(ExpectedConditions.visibilityOf(page.fromDateInput));
	}

	// ============================================================
	// CLICK SAVE
	// ============================================================

	public void clickSave() {

		wait.until(ExpectedConditions.elementToBeClickable(page.save));

		page.save.click();

		// Wait briefly for loader to appear.
		try {

			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

			shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

		} catch (org.openqa.selenium.TimeoutException ignored) {

			// Loader may not appear when request is very fast.
		}

		// Wait until save operation completes.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

		System.out.println("Save operation completed.");
	}

	// ============================================================
	// NAVIGATE TO MY LEAVE LIST
	// ============================================================

	public void navigateToMyLeaveList() {

		helper.waitForElement(page.leaveMenu);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", page.leaveMenu);

		System.out.println("Clicked 'Leave' in sidebar.");

		helper.waitForElement(page.myLeaveSubMenu);

		helper.clickElement(page.myLeaveSubMenu);

		System.out.println("Clicked 'My Leave' sub-menu.");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));

		wait.until(ExpectedConditions.visibilityOfAllElements(page.leaveListRows));

		System.out.println("My Leave list page loaded successfully.");
	}

	// ============================================================
	// CANCEL APPLIED LEAVE
	// ============================================================

	public void cancelAppliedLeave(String leaveType) {

		List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElements(page.leaveListRows));

		WebElement targetRow = null;

		for (WebElement row : rows) {

			String rowText = row.getText();

			boolean matchesType = rowText.contains(leaveType);

			boolean matchesDate = appliedFromDate == null || rowText.contains(appliedFromDate);

			if (matchesType && matchesDate) {

				targetRow = row;

				break;
			}
		}

		Assert.assertNotNull("No leave request row matched " + "leaveType='" + leaveType + "' and date='"
				+ appliedFromDate + "'. Rows currently listed: " + rows.size(), targetRow);

		// Find row-level Cancel button.
		WebElement cancelBtn = targetRow.findElement(By.xpath(".//button[normalize-space()='Cancel']"));

		// Click Cancel.
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cancelBtn);

		System.out.println(
				"Clicked row-level Cancel button " + "for leave type: " + leaveType + " on " + appliedFromDate);

		// Check whether confirmation dialog appears.
		try {

			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

			WebElement confirmBtn = shortWait.until(ExpectedConditions.elementToBeClickable(page.confirmCancelButton));

			confirmBtn.click();

			System.out.println(
					"Confirmed cancellation dialog " + "for leave type: " + leaveType + " on " + appliedFromDate);

		} catch (org.openqa.selenium.TimeoutException e) {

			System.out.println("No confirmation dialog appeared - " + "cancellation likely applied directly.");
		}

	} // IMPORTANT: closes cancelAppliedLeave()

	// ============================================================
	// VERIFY CANCEL SUCCESS
	// ============================================================

	public void confirmCancelMessage() {

		try {

			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));

			shortWait.until(ExpectedConditions.visibilityOf(page.cancelSuccessMsg));

			Assert.assertTrue("Expected leave-cancelled confirmation " + "toast to be displayed",
					page.cancelSuccessMsg.isDisplayed());

			System.out.println("Leave cancellation confirmation " + "message displayed (toast).");

		} catch (org.openqa.selenium.TimeoutException e) {

			// OrangeHRM may update the row directly
			// without showing a toast.
			System.out.println("No cancel toast appeared - " + "checking row status directly instead.");

			List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElements(page.leaveListRows));

			WebElement targetRow = null;

			for (WebElement row : rows) {

				String rowText = row.getText();

				boolean matchesDate = appliedFromDate == null || rowText.contains(appliedFromDate);

				if (matchesDate) {

					targetRow = row;

					break;
				}
			}

			Assert.assertNotNull("No cancel toast appeared, and could " + "not find the leave row for date "
					+ appliedFromDate + " to verify its status.", targetRow);

			String rowText = targetRow.getText();

			Assert.assertTrue(
					"No cancel toast appeared, and the row " + "for date " + appliedFromDate
							+ " does not show a Cancelled " + "status. Row text: \"" + rowText + "\"",
					rowText.toLowerCase().contains("cancel"));

			System.out.println(
					"Leave cancellation confirmed via row " + "status (no toast shown). " + "Row text: " + rowText);
		}
	}
}
