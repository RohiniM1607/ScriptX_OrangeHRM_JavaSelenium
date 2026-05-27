package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class EmployeeEntitlementPage extends BasePage {

    Actions actions;
    JavascriptExecutor js;

    public EmployeeEntitlementPage() {
        super();
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    @FindBy(xpath = "//span[text()='Leave']")
    WebElement leaveMenu;

    @FindBy(xpath = "//span[text()='Entitlements ']")
    WebElement entitlementsMenu;

    @FindBy(xpath = "//a[text()='Employee Entitlements']")
    WebElement employeeEntitlementsOption;

    @FindBy(xpath = "//h5[normalize-space()='Leave Entitlements']")
    WebElement employeeEntitlementsTitle;

    @FindBy(xpath = "//label[text()='Employee Name']/parent::div/following-sibling::div//input")
    WebElement employeeNameInput;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    WebElement leaveTypeDropdown;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
    WebElement leavePeriodDropdown;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='orangehrm-container']")
    WebElement searchResultContainer;

    @FindBy(xpath = "//span[text()='Invalid']")
    WebElement invalidValidationMessage;

    @FindBy(xpath = "//span[text()='Required']")
    WebElement requiredValidationMessage;

    @FindBy(xpath = "//div[@role='listbox']//span")
    List<WebElement> dropdownOptions;
    
    @FindBy(xpath = "//label[text()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//span[contains(@class,'oxd-input-field-error-message')]")
    WebElement employeeNameRequiredMessage;
    
    public void navigateToLeaveEntitlementsPage() {

        helper.clickElement(leaveMenu);

        helper.waitForElementToBeClickable(entitlementsMenu);
        js.executeScript("arguments[0].click();", entitlementsMenu);

        helper.waitForElementToBeClickable(employeeEntitlementsOption);
        js.executeScript("arguments[0].click();", employeeEntitlementsOption);
    }

    public boolean isEmployeeEntitlementsPageDisplayed() {
        helper.waitForElement(employeeEntitlementsTitle);
        return employeeEntitlementsTitle.isDisplayed();
    }

    public void enterEmployeeName(String employeeName) {

        helper.clickElement(employeeNameInput);
        employeeNameInput.clear();
        employeeNameInput.sendKeys(employeeName);

        try {
            helper.waitForElementLocated(By.xpath("//div[@role='listbox']//span"));
            actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).perform();
        } 
        catch (Exception e) {
            System.out.println("Employee suggestion not selected: " + e.getMessage());
        }
    }
    
    public String getEmployeeNameRequiredMessage() {

        helper.waitForElement(employeeNameRequiredMessage);
        return employeeNameRequiredMessage.getText();
    }

    public void enterInvalidEmployeeName(String employeeName) {
        helper.clickElement(employeeNameInput);
        employeeNameInput.clear();
        employeeNameInput.sendKeys(employeeName);
        actions.sendKeys(Keys.TAB).perform();
    }

    public void selectLeaveType(String leaveType) {
        helper.clickElement(leaveTypeDropdown);
        if (leaveType.equalsIgnoreCase("CAN - Maternity")) {
            actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(500)).sendKeys(Keys.ENTER).perform();
        } 
        else if (leaveType.equalsIgnoreCase("CAN - Personal")) {
            actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(500)).sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(500)).sendKeys(Keys.ENTER).perform();
        }
        else if (leaveType.equalsIgnoreCase("CAN - Vacation")) {
            actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(500)).sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(500)).sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(500)).sendKeys(Keys.ENTER).perform();
        } 
        else {
            actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(500)).sendKeys(Keys.ENTER).perform();
        }
    }

    public void selectLeavePeriod(String leavePeriod) {
        helper.clickElement(leavePeriodDropdown);

        By optionLocator = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
        WebElement option = helper.waitForElementLocated(optionLocator);

        js.executeScript("arguments[0].scrollIntoView(true);", option);
        js.executeScript("arguments[0].click();", option);
    }

    public void clickSearchButton() {

        js.executeScript("arguments[0].scrollIntoView(true);", searchButton);
        helper.waitForElementToBeClickable(searchButton);
        js.executeScript("arguments[0].click();", searchButton);
    }

    public boolean isSearchResultDisplayed() {
        helper.waitForElement(searchResultContainer);
        return searchResultContainer.isDisplayed();
    }

    public boolean isInvalidValidationDisplayed() {
        helper.waitForElement(invalidValidationMessage);
        return invalidValidationMessage.isDisplayed();
    }

    public boolean isRequiredValidationDisplayed() {
        helper.waitForElement(requiredValidationMessage);
        return requiredValidationMessage.isDisplayed();
    }
}