package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PIMPage extends BasePage {

    @FindBy(xpath = "//span[text()='PIM']")
    WebElement pimMenu;

    @FindBy(xpath = "//a[text()='Add Employee']")
    WebElement addEmployeeMenu;

    @FindBy(name = "firstName")
    WebElement firstNameTxt;

    @FindBy(name = "lastName")
    WebElement lastNameTxt;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    WebElement employeeIdTxt;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveBtn;

    @FindBy(xpath = "//h6[text()='Personal Details']")
    WebElement personalDetailsHeader;

    @FindBy(xpath = "//span[text()='Required']")
    WebElement requiredMsg;

    public void clickPIMMenu() {
        helper.clickElement(pimMenu);
    }

    public void clickAddEmployee() {
        helper.clickElement(addEmployeeMenu);
    }

    public void enterFirstName(String firstName) {
        helper.enterText(firstNameTxt, firstName);
    }

    public void enterLastName(String lastName) {
        helper.enterText(lastNameTxt, lastName);
    }

    public void enterEmployeeId(String empId) {
        helper.enterText(employeeIdTxt, empId);
    }

    public void clickSaveButton() {
        helper.clickElement(saveBtn);
    }

    public boolean isPersonalDetailsDisplayed() {
        try {
            return personalDetailsHeader.isDisplayed();
        } 
        catch (Exception e) {
            return false;
        }
    }

    public boolean isRequiredMessageDisplayed() {
        try {
            return requiredMsg.isDisplayed();
        }
        catch (Exception e) {
            return false;
        }
    }
}