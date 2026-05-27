package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalDetailsPage extends BasePage {

    @FindBy(xpath = "//label[text()='License Expiry Date']/following::input[1]")
    public WebElement LicenseExpiryDate;

    @FindBy(xpath = "//label[text()='Nationality']/following::div[contains(@class,'oxd-select-text')][1]")
    public WebElement Nationality;

    @FindBy(xpath = "//label[text()='Marital Status']/following::div[contains(@class,'oxd-select-text')][1]")
    public WebElement MaritalStatus;

    @FindBy(xpath = "//label[normalize-space()='Female']")
    public WebElement radioFemale;

    @FindBy(xpath = "//label[text()='Blood Type']/following::div[contains(@class,'oxd-select-text')][1]")
    public WebElement BloodType;

    @FindBy(xpath = "//label[text()='Test_Field']/following::input[1]")
    public WebElement TestField;

    @FindBy(xpath = "(//button[normalize-space()='Save'])[1]")
    public WebElement Pdsave;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast ')]//p[contains(@class,'oxd-text')]")
    public WebElement Pdsuccess;
    
    @FindBy(xpath = "//i[@class=\"oxd-icon bi-plus\"]")
    public WebElement addicon;

    @FindBy(xpath = "//input[@type='file']")
    public WebElement fileinput;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space\"]")
    public WebElement Ppsave;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast-content')]//p[1]")
    public WebElement Ppsuccess;
    
    @FindBy(xpath = "//div[@role='listbox']")
    public WebElement dropdownListbox;
    
    @FindBy(xpath = "//div[@role='listbox']//span")
    public List<WebElement> dropdownOptions;
}