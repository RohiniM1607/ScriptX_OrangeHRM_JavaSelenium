package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLeaveManagement_LeaveApprovel_Pages extends BasePage {

	@FindBy(xpath = "//span[text()='Leave']")
	public WebElement leaveMenu;

	@FindBy(xpath = "//a[text()='Leave List']")
	public WebElement leaveListSubMenu;

	@FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[1]")
	public WebElement fromDateInput;

	@FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
	public WebElement toDateInput;

	@FindBy(xpath = "//div[@class=\"oxd-multiselect-wrapper\"]//div[@class=\"oxd-select-text-input\"]")
	public WebElement statusDropdown;

	@FindBy(xpath = "//div[@role='listbox']//span")
	public List<WebElement> statusDropdownOptions;

	@FindBy(xpath = "//div[@class=\"oxd-multiselect-chips-area\"]/span")
	public List<WebElement> selectedStatusOption;

	@FindBy(xpath = "//i[@class=\"oxd-icon bi-x --clear\"]")
	public WebElement removeSelectedStatus;

	@FindBy(xpath = "//button[@type='submit' and normalize-space()='Search']")
	public WebElement searchButton;

	@FindBy(xpath = "//div[@role='row' and not(contains(@class,'oxd-table-header'))]")
	public List<WebElement> leaveResultRows;

	@FindBy(xpath = "//div[@role='row' and not(contains(@class,'oxd-table-header'))]//div[@role='cell'][7]//div")
	public List<WebElement> leaveStatusCells;

	@FindBy(xpath = "(//div[@role='row' and not(contains(@class,'oxd-table-header'))])[1]")
	public WebElement firstLeaveRow;

	@FindBy(xpath = "//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-paper-container']/div[@class='orangehrm-container']/div[@role='table']/div[@role='rowgroup']/div[1]/div[1]/div[9]/div[1]/button[1]")
	public WebElement approveButton;

	@FindBy(css = "div[role='table'] div:nth-child(1) div:nth-child(1) div:nth-child(9) div:nth-child(1) button:nth-child(2)")
	public WebElement rejectButton;

	@FindBy(xpath = "//div[contains(@class,'oxd-toast--success')]//p[2]")
	public WebElement successToastMessage;

	@FindBy(xpath = "//span[contains(text(),'No Records Found')]")
	public WebElement noRecordsFound;

	@FindBy(xpath = "//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='oxd-table-filter']/div[@class='oxd-table-filter-area']/form[@class='oxd-form']/div[@class='oxd-form-row']/div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[4]/div[1]/div[2]/div[1]/div[1]")
	public WebElement leaveType;

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	public WebElement employeeName;

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
	public WebElement errorMsg;

	@FindBy(xpath = "//div[@role='listbox']")
	public List<WebElement> empOption;

	@FindBy(xpath = "//div[text()='Searching...']")
	public WebElement searching;
}