package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalDetailsPage extends BasePage {

    @FindBy(xpath = "//label[text()='License Expiry Date']/following::input[1]")
    public WebElement LicenseExpiryDate;

    @FindBy(xpath = "//label[text()='Nationality']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]")
    public WebElement Nationality;

    @FindBy(xpath = "//label[text()='Marital Status']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]")
    public WebElement MaritalStatus;

    @FindBy(xpath = "//label[normalize-space()='Female']")
    public WebElement radioFemale;

    @FindBy(xpath = "//label[text()='Blood Type']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]")
    public WebElement BloodType;

    @FindBy(xpath = "//label[text()='Test_Field']/following::input[1]")
    public WebElement TestField;

    @FindBy(xpath = "(//button[normalize-space()='Save'])[1]")
    public WebElement btnSave;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast ')]//p[contains(@class,'oxd-text')]")
    public WebElement txtSuccessMessage;
}