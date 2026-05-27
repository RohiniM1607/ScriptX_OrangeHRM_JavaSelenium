package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmergencyContactsPage extends BasePage {

    @FindBy(xpath = "//h6[text()='Assigned Emergency Contacts']//following::button[contains(@class, 'oxd-button')][1]")
    public WebElement btnAddEmergencyContact;

    @FindBy(xpath = "//label[text()='Name']//following::input[1]")
    public WebElement inputName;

    @FindBy(xpath = "//label[text()='Relationship']/following::input[1]")
    public WebElement inputRelationship;

    @FindBy(xpath = "//label[text()='Home Telephone']/following::input[1]")
    public WebElement inputHomeTelephone;

    @FindBy(xpath = "//label[text()='Mobile']/following::input[1]")
    public WebElement inputMobile;

    @FindBy(xpath = "//label[text()='Work Telephone']/following::input[1]")
    public WebElement inputWorkTelephone;

    @FindBy(xpath = "(//div[contains(@class,'orangehrm-card-container')])[1]//button[normalize-space()='Save']")
    public WebElement btnSaveEmergencyContact;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast-content')]//p[1]")
    public WebElement txtSuccessMessage;

    @FindBy(xpath = "//h6[text()='Attachments']/following::button[1]")
    public WebElement btnAddAttachment;

    @FindBy(xpath = "//input[@type='file']")
    public WebElement fileInput;

    @FindBy(xpath = "//input[@type='file']/ancestor::form//button[normalize-space()='Save']")
    public WebElement btnSaveAttachment;
}