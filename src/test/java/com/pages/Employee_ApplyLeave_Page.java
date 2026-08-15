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

    @FindBy(xpath = "//a[contains(@class,'oxd-topbar-body-nav-tab-item') and normalize-space()='My Leave']")
    public WebElement myLeaveSubMenu;
 
    
    @FindBy(xpath = "//div[@role='listbox']//span")
    public List<WebElement> leaveTypeOptions;

    @FindBy(xpath = "//*[contains(text(),'Day(s)')]")
    public WebElement leaveBalanceText;

    @FindBy(xpath = "(//div[@class='oxd-date-input'])[1]")
    public WebElement fromDateInput;

    @FindBy(xpath = "(//div[@class='oxd-date-input'])[2]")
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

    @FindBy(xpath = "//label[text()='Leave Type']/ancestor::div[contains(@class,'oxd-input-group')]//span[contains(@class,'oxd-input-field-error-message')]")
    public WebElement leaveTypeRequiredError;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast') and .//p[text()='Successfully Saved']]")
    public WebElement confirm;
    
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement save;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[contains(@class,'oxd-table-row')]")
    public List<WebElement> leaveListRows;

    @FindBy(xpath = "//button[normalize-space()='Yes, Cancel']")
    public WebElement confirmCancelButton;

    @FindBy(xpath = "//button[normalize-space()='No, Cancel']")
    public WebElement dismissCancelButton;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast--success')]//p[contains(text(),'Successfully')]")
    public WebElement cancelSuccessMsg;
}
