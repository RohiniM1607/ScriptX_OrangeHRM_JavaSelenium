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

    @FindBy(xpath = "//label[text()='Status']//following-sibling::div//div[contains(@class,'oxd-select-text')]")
    public WebElement statusDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span")
    public List<WebElement> statusDropdownOptions;
    
    @FindBy(xpath="//div[@class=\"oxd-multiselect-chips-area\"]/span")
    public List<WebElement> selectedStatusOption;
    
    @FindBy(xpath="//i[@class=\"oxd-icon bi-x --clear\"]")
    public WebElement removeSelectedStatus;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//div[@role='row' and not(contains(@class,'oxd-table-header'))]")
    public List<WebElement> leaveResultRows;

    @FindBy(xpath = "//div[@role='row' and not(contains(@class,'oxd-table-header'))]//div[@role='cell'][7]//div")
    public List<WebElement> leaveStatusCells;

    @FindBy(xpath = "(//div[@role='row' and not(contains(@class,'oxd-table-header'))])[1]")
    public WebElement firstLeaveRow;

    @FindBy(xpath = "//button[normalize-space()='Approve']")
    public WebElement approveButton;

    @FindBy(xpath = "//button[normalize-space()='Reject']")
    public WebElement rejectButton;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast--success')]//p[2]")
    public WebElement successToastMessage;

    @FindBy(xpath = "//span[contains(text(),'No Records Found')]")
    public WebElement noRecordsFound;
}
