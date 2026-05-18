package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CreateUserCredentialsPage extends BasePage {

    Actions actions;

    public CreateUserCredentialsPage() {
        super();
        this.actions = new Actions(driver);
    }

    @FindBy(xpath = "//span[text()='Admin']")
    WebElement adminMenu;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addBtn;

    @FindBy(xpath = "//h6[text()='Add User']")
    WebElement addUserTitle;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    WebElement userRoleDropDown;

    @FindBy(xpath = "//label[text()='Employee Name']/parent::div/following-sibling::div//input")
    WebElement empName;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
    WebElement statusDropDown;

    @FindBy(xpath = "//label[text()='Username']/parent::div/following-sibling::div//input")
    WebElement userName;

    @FindBy(xpath = "//label[text()='Password']/parent::div/following-sibling::div//input")
    WebElement password;

    @FindBy(xpath = "//label[text()='Confirm Password']/parent::div/following-sibling::div//input")
    WebElement confirmPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveBtn;

    @FindBy(xpath = "//p[contains(@class,'oxd-text--toast-message')]")
    WebElement successToast;

    @FindBy(xpath = "//span[text()='Required']")
    List<WebElement> requiredValidationMessages;

    public void navigateToAdmin() {
        helper.clickElement(adminMenu);
    }

    public void clickAddBtn() {
        helper.clickElement(addBtn);
    }

    public boolean isAddUserPageDisplayed() {
        helper.waitForElement(addUserTitle);
        return addUserTitle.isDisplayed();
    }

    public void pressDownAndEnter(int count) {
        for (int i = 0; i < count; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofSeconds(1));
        }
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void selectUserRole(String role) {
        helper.clickElement(userRoleDropDown);

        if (role.equalsIgnoreCase("Admin")) {
            pressDownAndEnter(1);
        } 
        else if (role.equalsIgnoreCase("ESS")) {
            pressDownAndEnter(2);
        }
    }

    public void enterEmployeeName(String employeeName) {
        helper.clickElement(empName);
        empName.sendKeys(employeeName);

        helper.waitForElement(driver.findElement(By.xpath("//div[@role='listbox']//span")));
        pressDownAndEnter(1);
    }

    public void selectStatus(String status) {
        helper.clickElement(statusDropDown);

        if (status.equalsIgnoreCase("Enabled")) {
            pressDownAndEnter(1);
        } 
        else if (status.equalsIgnoreCase("Disabled")) {
            pressDownAndEnter(2);
        }
    }

    public void enterUsername(String username) {
        helper.enterText(userName, username);
    }

    public void enterPassword(String pwd) {
        helper.enterText(password, pwd);
    }

    public void enterConfirmPassword(String confirmPwd) {
        helper.enterText(confirmPassword, confirmPwd);
    }

    public void clickSaveButton() {
        helper.clickElement(saveBtn);
    }

    public boolean isSuccessMessageDisplayed() {
        helper.waitForElement(successToast);
        return successToast.isDisplayed();
    }

    public boolean isRequiredValidationMessageDisplayed() {

        String expectedValidation = "Required";

        if (requiredValidationMessages.size() == 0) {
            return false;
        }

        helper.waitForElement(requiredValidationMessages.get(0));

        for (WebElement message : requiredValidationMessages) {
            if (!message.getText().equalsIgnoreCase(expectedValidation)) {
                return false;
            }
        }

        return true;
    }
}