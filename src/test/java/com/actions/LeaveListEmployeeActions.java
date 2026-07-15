package com.actions;

import com.pages.LeaveListEmployeePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LeaveListEmployeeActions {

    private WebDriver driver;
    private WebDriverWait wait;
    private LeaveListEmployeePage page;

    public LeaveListEmployeeActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.page = PageFactory.initElements(driver,LeaveListEmployeePage.class);
    }

   

    private void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    private void waitForInvisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    private WebElement waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    private String getElementText(WebElement element) {
        return waitForElement(element).getText().trim();
    }

  

    public void navigateToMyLeavePage() {
        clickElement(page.leaveMenu);
        clickElement(page.myLeaveSubMenu);
        waitForInvisibility(page.loader);
    }

    public void selectLeaveType(String leaveTypeName) {
        clickElement(page.leaveTypeDropdown);
        WebElement option = driver.findElement(
                By.xpath(String.format(page.leaveTypeOption, leaveTypeName)));
        clickElement(option);
    }

    public void clickSearch() {
        clickElement(page.searchButton);
        waitForInvisibility(page.loader);
    }

    public int getTotalRows() {
        List<WebElement> rows = driver.findElements(By.xpath("//div[@role='row']"));
        return rows.size();
    }

    public String getStatusForLeaveType(String leaveTypeName) {
        int totalRows = getTotalRows();
        for (int rowIndex = 1; rowIndex <= totalRows; rowIndex++) {
            WebElement leaveTypeEl = driver.findElement(
                    By.xpath(String.format(page.leaveTypeCellByRow, rowIndex)));
            if (leaveTypeEl.getText().trim().equalsIgnoreCase(leaveTypeName)) {
                WebElement statusEl = driver.findElement(
                        By.xpath(String.format(page.statusCellByRow, rowIndex)));
                return statusEl.getText().trim();
            }
        }
        return null;
    }

    public String searchLeaveAndGetStatus(String leaveTypeName) {
        navigateToMyLeavePage();
        selectLeaveType(leaveTypeName);
        clickSearch();
        return getStatusForLeaveType(leaveTypeName);
    }

    public String withoutApplyingLeave() {
        navigateToMyLeavePage();
        clickSearch();
        return getElementText(page.recordCountText);
    }

    public String getLeaveBalanceForLeaveType(String leaveTypeName) {
        selectLeaveType(leaveTypeName);
        clickSearch();
        int totalRows = getTotalRows();
        for (int rowIndex = 1; rowIndex <= totalRows; rowIndex++) {
            WebElement leaveTypeEl = driver.findElement(
                    By.xpath(String.format(page.leaveTypeCellByRow, rowIndex)));
            if (leaveTypeEl.getText().trim().equalsIgnoreCase(leaveTypeName)) {
                WebElement balanceEl = driver.findElement(
                        By.xpath(String.format(page.leaveBalanceCellByRow, rowIndex)));
                return balanceEl.getText().trim();
            }
        }
        return null;
    }
}