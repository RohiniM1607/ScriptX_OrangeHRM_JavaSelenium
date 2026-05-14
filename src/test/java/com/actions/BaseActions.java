package com.actions;

import org.openqa.selenium.WebDriver;

import com.hooks.Hooks;

public class BaseActions {

    public WebDriver driver;

    public BaseActions() {
        driver = Hooks.driver;
    }
}