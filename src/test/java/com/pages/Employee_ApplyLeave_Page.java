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
 

    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[1]")
    public WebElement leaveTypeDropdown;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown']//span[not(@class)]")
    public List<WebElement> leaveTypeOptions;

    @FindBy(xpath = "//*[contains(text(),'Day(s)')]")
    public WebElement leaveBalanceText;

    @FindBy(xpath = "(//input[@placeholder='yyyy-mm-dd'])[1]")
    public WebElement fromDateInput;

    @FindBy(xpath = "(//input[@placeholder='yyyy-mm-dd'])[2]")
    public WebElement toDateInput;

    @FindBy(xpath = "//textarea[@placeholder='Type here']")
    public WebElement commentsTextarea;

    @FindBy(xpath = "//button[normalize-space()='Apply']")
    public WebElement applyButton;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast--success')]//p[2]")
    public WebElement successMsg;
}