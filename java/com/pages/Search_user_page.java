package com.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Search_user_page extends BasePage {

    @FindBy(xpath = "//a[@class='oxd-main-menu-item active']")
    public WebElement Admin_page;

    @FindBy(xpath = "//label[text()='Username']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    public WebElement username_field;

    @FindBy(xpath="//h5[text()='System Users']")
    public WebElement system_user;
    
    @FindBy(xpath = "//label[normalize-space()='User Role']/../..//div[@class='oxd-select-text-input']")
    public WebElement userRole;

    @FindBy(xpath = "//div[@role='listbox']//div[@role='option']")
    public List<WebElement> userRoleOptions;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    public WebElement employeeName;  
    
    @FindBy(xpath = "//div[@role='listbox']//div[@role='option']")
    public List<WebElement> employeeSuggestions;

    @FindBy(css = "button[type='submit']")
    public WebElement search_btn;

    @FindBy(xpath = "//div[@role='rowgroup']//div[1]//div[1]//div[2]//div[1]")
    public WebElement username_record;

    @FindBy(xpath = "//div[@role='rowgroup']//div[1]//div[1]//div[3]//div[1]")
    public WebElement userrole_record;

    @FindBy(xpath = "//div[@role='rowgroup']//div[1]//div[1]//div[4]//div[1]")
    public WebElement employeeName_record;

    @FindBy(xpath = "//div[@role='listbox']")
    public WebElement listbox;

}