package com.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DependentsPage extends BasePage {

    @FindBy(xpath = "//button[normalize-space()='Add']")
    public WebElement addIcon;

    @FindBy(xpath = "//label[text()='Name']/following::input[1]")
    public WebElement nameInput;

    @FindBy(xpath = "//label[text()='Relationship']/following::div[contains(@class,'oxd-select-text')][1]")
    public WebElement relationshipDropdown;

    @FindBy(xpath = "//label[text()='Date of Birth']/following::input[1]")
    public WebElement dateOfBirth;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast ')]//p[contains(@class,'oxd-text')]")
    public WebElement successMessage;

    @FindBy(xpath = "//div[@role='listbox']")
    public WebElement dropdownListbox;

    @FindBy(xpath = "//div[@role='listbox']//span")
    public List<WebElement> dropdownOptions;
}