package com.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalaryPage extends BasePage {

    // "+ Add" button inside Assigned Salary Components section
	@FindBy(xpath = "//button[contains(normalize-space(),'Add')]")
	public WebElement addButton;

    // Salary Component — text input (required)
    @FindBy(xpath = "//label[text()='Salary Component']/following::input[1]")
    public WebElement salaryComponentInput;

    // Pay Grade — dropdown (optional)
    @FindBy(xpath = "//label[text()='Pay Grade']/following::div[contains(@class,'oxd-select-text')][1]")
    public WebElement payGradeDropdown;

    // Pay Frequency — dropdown (optional)
    @FindBy(xpath = "//label[text()='Pay Frequency']/following::div[contains(@class,'oxd-select-text')][1]")
    public WebElement payFrequencyDropdown;

    // Currency — dropdown (required)
    @FindBy(xpath = "//label[text()='Currency']/following::div[contains(@class,'oxd-select-text')][1]")
    public WebElement currencyDropdown;

    // Amount — text input (required)
    @FindBy(xpath = "//label[text()='Amount']/following::input[1]")
    public WebElement amountInput;

    // Comments — textarea
    @FindBy(xpath = "//label[text()='Comments']/following::textarea[1]")
    public WebElement commentsInput;

    // Save button scoped to the salary form card
    @FindBy(xpath = "//div[contains(@class,'orangehrm-card-container')]//button[normalize-space()='Save']")
    public WebElement saveButton;

    // Success toast message
    @FindBy(xpath = "//div[contains(@class,'oxd-toast')]//p[contains(@class,'oxd-text')]")
    public WebElement successMessage;

    // Shared dropdown listbox and options
    @FindBy(xpath = "//div[@role='listbox']")
    public WebElement dropdownListbox;

    @FindBy(xpath = "//div[@role='listbox']//span")
    public List<WebElement> dropdownOptions;
}