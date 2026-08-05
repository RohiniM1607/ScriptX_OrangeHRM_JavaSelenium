package com.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Employee_ApplyLeave_Page extends BasePage {
	@FindBy(css = "div.oxd-form-loader")
	public WebElement loader;

	 @FindBy(xpath = "//div[contains(@class,'oxd-toast')]//button[contains(@class,'oxd-toast-close')]")
	    public WebElement closeInfoNotification;

    @FindBy(xpath = "//span[contains(@class,'oxd-main-menu-item--name') and text()='Leave']")
    public WebElement leaveMenu;

  
    @FindBy(xpath = "//a[contains(@class,'oxd-topbar-body-nav-tab-item') and normalize-space()='Apply']")
    public WebElement applySubMenu;
 
    
    @FindBy(xpath = "//div[@role='listbox']//span")
    public List<WebElement> leaveTypeOptions;

    @FindBy(xpath = "//*[contains(text(),'Day(s)')]")
    public WebElement leaveBalanceText;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[1]")
    public WebElement fromDateInput;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
    public WebElement toDateInput;

    @FindBy(xpath = "//textarea[@placeholder='Type here']")
    public WebElement commentsTextarea;

    @FindBy(xpath = "//button[normalize-space()='Apply']")
    public WebElement applyButton;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast--success')]//p[2]")
    public WebElement successMsg;
    
    @FindBy(xpath = "//div[@role='listbox']")
    public WebElement empOption;
    
    @FindBy(xpath = "//label[text()='Leave Type']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text-input')]")
    public WebElement leave_type;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast') and .//p[text()='Successfully Saved']]")
    public WebElement confirm;
    
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement save;
}