package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePicturePage extends BasePage {
	
	@FindBy(xpath = "//i[@class=\"oxd-icon bi-plus\"]")
	public WebElement addIcon;

    @FindBy(xpath = "//input[@type='file']")
    public WebElement fileInput;

    @FindBy(xpath = "//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space\"]")
    public WebElement btnSave;
    
    @FindBy(xpath = "//div[contains(@class,'oxd-toast-content')]//p[1]")
    public WebElement txtSuccessMessage;
}