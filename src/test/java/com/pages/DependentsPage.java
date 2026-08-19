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
    
    @FindBy(xpath = "//div[h6[text()='Attachments']]//button[normalize-space()='Add']")
    public WebElement attachmentAddButton;

    @FindBy(xpath = "//input[@type='file']")
    public WebElement fileInput;

    @FindBy(xpath = "//label[text()='Comment']/following::textarea[1]")
    public WebElement commentInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton1;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    public WebElement cancelButton;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast ')]//p[contains(@class,'oxd-text')]")
    public WebElement successMessage1;

    @FindBy(xpath = "//span[contains(@class,'oxd-input-field-error-message') or contains(text(),'Attachment Size Exceeded')]")
    public WebElement fileSizeErrorMessage;
}