package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.utilities.HelperClass;

public class BasePage {
	protected  WebDriver driver;
	protected  HelperClass helper;

    public BasePage() {
        helper = new HelperClass();
        driver = helper.getDriver();
        PageFactory.initElements(driver, this);
    }
}