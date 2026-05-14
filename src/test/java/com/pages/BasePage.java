package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.hooks.Hooks;

public class BasePage {
    public WebDriver driver;

    public BasePage() {
        driver = Hooks.driver;
        PageFactory.initElements(driver, this);
    }
}