package com.actions;

import org.openqa.selenium.WebDriver;

import com.utilities.HelperClass;

public class BaseActions {

    public WebDriver driver;

    public BaseActions() {
        driver = HelperClass.driver.get();
    }
}