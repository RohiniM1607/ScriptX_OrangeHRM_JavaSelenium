package com.actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.pages.LeaveListPage;
import com.utilities.HelperClass;

public class LeaveListActions extends BaseActions {

    LeaveListPage page = new LeaveListPage();
    HelperClass helper = new HelperClass();

    public void navigateToLeaveListPage() {
        helper.clickElement(page.leaveMenu);
        helper.clickElement(page.leaveListMenu);
        helper.waitForElement(page.searchButton);
    }

    public boolean verifyLeaveListPageDisplayed() {
        helper.waitForElement(page.searchButton);
        return page.searchButton.isDisplayed();
    }

    public void clickSearchButton() {
        helper.clickElement(page.searchButton);
    }

//    public void searchLeaveRequestByEmployee(String employeeName) {
//
//        helper.enterText(page.employeeNameTextbox, employeeName);
//
//        helper.waitForElement(page.employeeSuggestion);
//
//        helper.pressDownAndEnter(1);
//
//        helper.clickElement(page.searchButton);
//
//    }

//    public void filterLeaveRequestByStatus(String status) {
//
//        helper.clickElement(page.statusDropdown);
//
//        helper.waitForElement(page.statusList);
//
//        helper.pressDownAndEnter(getStatusIndex(status));
//
//        helper.clickElement(page.searchButton);
//
//    }

//    private int getStatusIndex(String status) {
//
//        switch (status.toLowerCase()) {
//
//        case "pending approval":
//            return 1;
//
//        case "scheduled":
//            return 2;
//
//        case "taken":
//            return 3;
//
//        default:
//            return 1;
//        }
//
//    }
//
//    public void enterFromDate(String fromDate) {
//
//        helper.waitForElement(page.fromDateTextbox);
//
//        page.fromDateTextbox.sendKeys(Keys.CONTROL + "a");
//        page.fromDateTextbox.sendKeys(Keys.DELETE);
//        page.fromDateTextbox.sendKeys(fromDate);
//
//    }
//
//    public void enterToDate(String toDate) {
//
//        helper.waitForElement(page.toDateTextbox);
//
//        page.toDateTextbox.sendKeys(Keys.CONTROL + "a");
//        page.toDateTextbox.sendKeys(Keys.DELETE);
//        page.toDateTextbox.sendKeys(toDate);
//
//    }

    public boolean verifySearchResultDisplayed() {
        try {
            if (page.noRecordFound.isDisplayed()) {
                System.out.println("No records found.");
            }
        } catch (Exception e) {
        }

        for (WebElement row : page.resultRows) {
            if (row.isDisplayed()) {
                System.out.println("Search results are displayed.");
                return true;
            }
        }

        return false;
    }

//    public boolean verifyNoRecordFound() {
//
//        try {
//
//            helper.waitForElement(page.noRecordFound);
//            return page.noRecordFound.isDisplayed();
//
//        } catch (Exception e) {
//
//            return false;
//
//        }

 //   }

}