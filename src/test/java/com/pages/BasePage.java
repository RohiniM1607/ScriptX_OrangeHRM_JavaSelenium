package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.utilities.HelperClass;

public class BasePage {
    public WebDriver driver;
    public HelperClass helper;
    
    public BasePage() {
    	driver = HelperClass.driver;
    	helper = new HelperClass();
        PageFactory.initElements(helper.getDriver(), this);
    }
}