package com.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeaveListPage extends BasePage {

    public LeaveListPage() {
        super();
    }

    @FindBy(xpath = "//span[text()='Leave']")
    WebElement leaveMenu;

    @FindBy(xpath = "//a[text()='Leave List']")
    WebElement leaveListMenu;

    @FindBy(xpath = "//h5[text()='Leave List']")
    WebElement leaveListTitle;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    WebElement leaveStatusDropdown;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='orangehrm-container']")
    WebElement searchResultContainer;
    
    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[1]")
    WebElement fromDate;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
    WebElement toDate;

    

    public void navigateToLeaveListPage() {

        helper.clickElement(leaveMenu);
        helper.clickElement(leaveListMenu);
    }

    public boolean isLeaveListPageDisplayed() {

        helper.waitForElement(leaveListTitle);
        return leaveListTitle.isDisplayed();
    }

    public void selectLeaveStatus(String status) {

        helper.clickElement(leaveStatusDropdown);

        if(status.equalsIgnoreCase("Pending Approval")) {

            helper.pressDownAndEnter(1);
        }
        else if(status.equalsIgnoreCase("Scheduled")) {

            helper.pressDownAndEnter(2);
        }
        else if(status.equalsIgnoreCase("Taken")) {

            helper.pressDownAndEnter(3);
        }
    }

    public void clickSearchButton() {

        helper.clickElement(searchButton);
    }

    public boolean isSearchResultDisplayed() {

        helper.waitForElement(searchResultContainer);
        return searchResultContainer.isDisplayed();
    }
    
    public void enterFromDate(String date) {

        helper.waitForElement(fromDate);

        fromDate.sendKeys(Keys.CONTROL + "a");
        fromDate.sendKeys(Keys.DELETE);

        fromDate.sendKeys(date);
    }

    public void enterToDate(String date) {

        helper.waitForElement(toDate);

        toDate.sendKeys(Keys.CONTROL + "a");
        toDate.sendKeys(Keys.DELETE);

        toDate.sendKeys(date);
    }

    
}