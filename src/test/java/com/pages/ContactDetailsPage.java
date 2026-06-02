package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactDetailsPage extends BasePage {

    @FindBy(xpath = "//label[text()='Street 1']//following::input[contains(@class, 'oxd-input')][1]")
    public WebElement street1;

    @FindBy(xpath = "//label[text()='Street 2']/following::input[1]")
    public WebElement street2;

    @FindBy(xpath = "//label[text()='City']/following::input[1]")
    public WebElement city;

    @FindBy(xpath = "//label[text()='State/Province']/following::input[1]")
    public WebElement state;

    @FindBy(xpath = "//label[text()='Zip/Postal Code']/following::input[1]")
    public WebElement zipCode;

    @FindBy(xpath = "//label[text()='Country']//following::div[1]")
    public WebElement country;

    @FindBy(xpath = "//label[text()='Home']/following::input[1]")
    public WebElement homeTelephone;

    @FindBy(xpath = "//label[text()='Mobile']/following::input[1]")
    public WebElement mobile;

    @FindBy(xpath = "//label[text()='Work']/following::input[1]")
    public WebElement workTelephone;

    @FindBy(xpath = "//label[text()='Work Email']/following::input[1]")
    public WebElement workEmail;

    @FindBy(xpath = "(//div[contains(@class,'orangehrm-card-container')])[1]//button[normalize-space()='Save']")
    public WebElement btnSave;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast-content')]//p[1]")
    public WebElement txtSuccessMessage;

    @FindBy(xpath = "//h6[text()='Attachments']/following::button[1]")
    public WebElement addIcon;

   @FindBy(xpath = "//input[@type='file']")
    public WebElement fileInput;

   @FindBy(xpath = "//div[contains(@class,'orangehrm-attachment')]//button[normalize-space()='Save']")
   public WebElement btnAttachmentSave;
    
    @FindBy(xpath = "//div[@role='listbox']")
    public WebElement dropdownListbox;
    
    @FindBy(xpath = "//div[@role='listbox']//span")
    public List<WebElement> dropdownOptions;
}