package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashBoardEmployeePage extends BasePage {

    @FindBy(xpath = "//span[normalize-space()='My Info']")
    public WebElement menuMyInfo;

    @FindBy(xpath = "//span[text()='Dashboard']")
    public WebElement dashboardHeader;
}