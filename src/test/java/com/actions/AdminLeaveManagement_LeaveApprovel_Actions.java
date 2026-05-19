package com.actions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.pages.AdminLeaveManagement_LeaveApprovel_Pages;
import com.utilities.HelperClass;

import java.time.Duration;

public class AdminLeaveManagement_LeaveApprovel_Actions extends BaseActions {

   AdminLeaveManagement_LeaveApprovel_Pages pages;
   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   HelperClass helper = new HelperClass();
    JavascriptExecutor js;
    
    public static List<String> collectedStatuses = new ArrayList<>();

    public AdminLeaveManagement_LeaveApprovel_Actions() {
        super();
        pages = new AdminLeaveManagement_LeaveApprovel_Pages();
        js = (JavascriptExecutor) driver;
    }

    public void navigateToLeaveList() {
        helper.clickElement(pages.leaveListSubMenu);
    }

    public void setDateRange(String fromDate, String toDate) {
        setDateField(pages.fromDateInput, fromDate);
        setDateField(pages.toDateInput, toDate);
    }

    private void setDateField(WebElement dateInput, String dateValue) {
//        js.executeScript("arguments[0].scrollIntoView({block:'center'});", dateInput);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(dateInput));

        dateInput.click();
        dateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), dateValue);
        dateInput.sendKeys(Keys.TAB);
    }

    public void selectStatus(String statusLabel) {
    	
    	for(WebElement op:pages.selectedStatusOption) {
    		pages.removeSelectedStatus.click();
    	}

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", pages.statusDropdown);
        helper.clickElement(pages.statusDropdown);
        wait.until(ExpectedConditions.visibilityOfAllElements(pages.statusDropdownOptions));

        for (WebElement option : pages.statusDropdownOptions) {
            if (option.getText().equalsIgnoreCase(statusLabel)) {
                option.click();
                return;
            }
        }
    }

    public void clickSearch() {
        // Scroll the search button into view before clicking
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", pages.searchButton);
        HelperClass helper = new HelperClass();
        helper.clickElement(pages.searchButton);
    }

    public void clickFirstLeaveRow() {
        wait.until(ExpectedConditions.visibilityOf(pages.firstLeaveRow));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", pages.firstLeaveRow);

        pages.firstLeaveRow.click();
    }

    public void clickApprove() {
        wait.until(ExpectedConditions.visibilityOf(pages.approveButton));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", pages.approveButton);
        js.executeScript("arguments[0].click();", pages.approveButton);
    }

    public void clickReject() {
        wait.until(ExpectedConditions.visibilityOf(pages.rejectButton));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", pages.rejectButton);
        js.executeScript("arguments[0].click();", pages.rejectButton);
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(pages.successToastMessage));
        return pages.successToastMessage.getText().trim();
    }

    public void verifySuccessMessageContains(String expectedText) {
        String actual = getSuccessMessage();
        Assert.assertTrue(
            actual.toLowerCase().contains(expectedText.toLowerCase()),
            "Expected toast to contain '" + expectedText + "' but got: '" + actual + "'"
        );
    }

    public void collectLeaveStatuses() {
        wait.until(ExpectedConditions.visibilityOfAllElements(pages.leaveStatusCells));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", pages.leaveStatusCells.get(0));

        collectedStatuses.clear();
        for (WebElement cell : pages.leaveStatusCells) {
            String status = cell.getText().trim();
            if (!status.isEmpty()) {
                collectedStatuses.add(status);
            }
        }
    }

    public void verifyAllStatusesMatch(String expectedStatus) {
        Assert.assertFalse(collectedStatuses.isEmpty(),
            "No statuses were collected from the Leave List table.");

        for (String status : collectedStatuses) {
            Assert.assertEquals(status, expectedStatus,
                "Expected all statuses to be '" + expectedStatus +
                "' but found: '" + status + "'");
        }
    }

	public void clickLeaveMenu() {
		pages.leaveMenu.click();
	}
}
