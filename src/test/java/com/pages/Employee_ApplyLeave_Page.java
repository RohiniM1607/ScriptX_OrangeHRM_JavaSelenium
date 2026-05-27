package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Employee_ApplyLeave_Page {
	
	 @FindBy(xpath = "//span[text()='Leave']")
	    public WebElement leaveMenu;

	    @FindBy(xpath = "//a[text()='Apply']")
	    public WebElement applySubMenu;

	    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[1]")
	    public WebElement leaveTypeDropdown;

	    @FindBy(xpath = "//div[@class='oxd-select-dropdown']//span")
	    public List<WebElement> leaveTypeOptions;

	    @FindBy(xpath = "//p[contains(text(),'Day(s)')]")
	    public WebElement leaveBalanceText;

	    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[1]")
	    public WebElement fromDateInput;

	    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
	    public WebElement toDateInput;

	    @FindBy(xpath = "//textarea[@placeholder='Type here']")
	    public WebElement commentsTextarea;

	    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Apply']")
	    public WebElement applyButton;

	    @FindBy(xpath = "//div[contains(@class,'oxd-toast--success')]//p[2]")
	    public WebElement successMsg;


}
