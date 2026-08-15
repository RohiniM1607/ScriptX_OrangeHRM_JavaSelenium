package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashBoardEmployeePage extends BasePage {

    @FindBy(xpath = "//span[normalize-space()='My Info']")
    public WebElement menuMyInfo;

    // Fix: OrangeHRM's Dashboard header is inside the top navigation bar
    // as a <span> inside an <a> tag. The sidebar item uses a different
    // structure. Use the breadcrumb/page header instead — it is always
    // present after login regardless of OrangeHRM version.
    @FindBy(xpath = "//*[self::h6 or self::span or self::p]" +
                    "[normalize-space()='Dashboard']")
    public WebElement dashboardHeader;

    @FindBy(xpath = "//img[@class='employee-image']")
    public WebElement Profile;

    @FindBy(xpath = "//a[normalize-space()='Contact Details']")
    public WebElement menuContactDetails;

    @FindBy(xpath = "//a[normalize-space()='Emergency Contacts']")
    public WebElement menuEmergencyContacts;

    @FindBy(xpath = "//a[normalize-space()='Dependents']")
    public WebElement menuDependents;
    
    @FindBy(xpath = "//a[normalize-space()='Salary']")
    public WebElement menuSalary;
}