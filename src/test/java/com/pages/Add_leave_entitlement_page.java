package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Add_leave_entitlement_page extends BasePage {
	

	@FindBy(xpath="//span[normalize-space()='Leave']")
	public WebElement leave_page;
	
	@FindBy(xpath="//span[normalize-space()='Entitlements']")
	public WebElement entitlement_link;
	
	@FindBy(xpath="//a[normalize-space()='Add Entitlements']")
	public WebElement add_entitlement;
	
	@FindBy(xpath="//input[@placeholder='Type for hints...']")
	public WebElement employee_name;
	
	@FindBy(xpath="//div[@role='listbox']")
	public WebElement empOption;
	
	@FindBy(xpath="//div[contains(text(),'-- Select --')]")
	public WebElement leave_type;
	
	@FindBy(xpath="//div[text()='CAN - Bereavement']")
	public WebElement leaveType_listBox;
	
	@FindBy(xpath="//div[contains(text(),'2026-01-01 - 2026-31-12')]")
	public WebElement leave_period;
	
	@FindBy(xpath="(//div[@class='oxd-input-group__label-wrapper'])[7]/parent::*/child::*[2]/child::*")
	public WebElement entitlement;
	
	@FindBy(css="button[type='submit']")
	public WebElement save;
	
	@FindBy(xpath="//button[normalize-space()='Confirm']")
	public WebElement confirm;

	@FindBy(xpath="//div[@class='oxd-layout-context']//div[2]//div[1]//div[1]//div[1]//span[1]")
	public WebElement name_require_field;
	
	@FindBy(xpath="//div[text()='Searching...']")
	public WebElement searching;
	 
}
