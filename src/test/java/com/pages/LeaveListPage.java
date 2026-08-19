package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeaveListPage extends BasePage {

    @FindBy(xpath = "//span[text()='Leave']")
    public WebElement leaveMenu;

    @FindBy(xpath = "//a[text()='Leave List']")
    public WebElement leaveListMenu;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    public WebElement employeeNameTextbox;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[1]")
    public WebElement fromDateTextbox;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
    public WebElement toDateTextbox;

    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[1]")
    public WebElement statusDropdown;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement searchButton;

    @FindBy(xpath = "//div[@class='oxd-table-body']")
    public WebElement resultTable;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    public List<WebElement> resultRows;

    @FindBy(xpath = "//span[text()='No Records Found']")
    public WebElement noRecordFound;
    
    @FindBy(xpath="//div[@role='listbox']")
    public WebElement employeeSuggestion;
    
    @FindBy(xpath="//div[@role='listbox']")
    public WebElement statusList;

}