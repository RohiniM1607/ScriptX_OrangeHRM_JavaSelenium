package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateUserCredentialsPage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public CreateUserCredentialsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
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

    public void navigateToAdmin() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
    }

    public void clickAddBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    public boolean isAddUserPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(addUserTitle)).isDisplayed();
    }

    public void pressDownAndEnter(int count) {
        for (int i = 0; i < count; i++) {
            actions.sendKeys(Keys.ARROW_DOWN)
                   .pause(Duration.ofSeconds(1));
        }

        actions.sendKeys(Keys.ENTER).perform();
    }

    public void selectUserRole(String role) {
        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropDown)).click();

        if (role.equalsIgnoreCase("Admin")) {
            pressDownAndEnter(1);
        } 
        else if (role.equalsIgnoreCase("ESS")) {
            pressDownAndEnter(2);
        }
    }

    public void enterEmployeeName(String employeeName) {
        wait.until(ExpectedConditions.elementToBeClickable(empName)).click();
        empName.sendKeys(employeeName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@role='listbox']//span")
        ));

        pressDownAndEnter(1);
    }

    public void selectStatus(String status) {
        wait.until(ExpectedConditions.elementToBeClickable(statusDropDown)).click();

        if (status.equalsIgnoreCase("Enabled")) {
            pressDownAndEnter(1);
        } 
        else if (status.equalsIgnoreCase("Disabled")) {
            pressDownAndEnter(2);
        }
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(userName)).sendKeys(username);
    }

    public void enterPassword(String pwd) {
        wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(pwd);
    }

    public void enterConfirmPassword(String confirmPwd) {
        wait.until(ExpectedConditions.elementToBeClickable(confirmPassword)).sendKeys(confirmPwd);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(successToast)).isDisplayed();
    }
}