package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LeaveListEmployeePage extends BasePage {

    @FindBy(xpath = "//span[normalize-space()='Leave']")
    public WebElement leaveMenu;

    @FindBy(xpath = "//a[normalize-space()='My Leave']")
    public WebElement myLeaveSubMenu;

    @FindBy(xpath = "//div[contains(@class,'oxd-loading-spinner')]")
    public WebElement loader;

    @FindBy(xpath = "//label[text()='Leave Type']/../..//div[contains(@class,'oxd-select-text')]")
    public WebElement leaveTypeDropdown;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//div[@role='row']")
    public List<WebElement> tableRows;

    @FindBy(xpath = "//div[contains(@class,'orangehrm-header-container')]//span[contains(@class,'oxd-text--span')]")
    public WebElement recordCountText;

    public String leaveTypeOption = "//div[@role='listbox']//span[text()='%s']";

    public String leaveTypeCellByRow =
            "//div[@class='oxd-table-body']//div[@role='row'][%d]//div[@role='cell'][4]/div";

    public String statusCellByRow =
            "//div[@class='oxd-table-body']//div[@role='row'][%d]//div[@role='cell'][7]/div";

    public String leaveBalanceCellByRow =
            "//div[@class='oxd-table-body']//div[@role='row'][%d]//div[@role='cell'][5]/div";
}