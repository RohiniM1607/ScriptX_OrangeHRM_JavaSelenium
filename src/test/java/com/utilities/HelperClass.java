package com.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperClass {

	WebDriver driver;

	public HelperClass(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void enterText(WebElement element, String value) {
		waitForElement(element);
		element.clear();
		element.sendKeys(value);
	}

	public void clickElement(WebElement element) {
		waitForElement(element);
		element.click();
	}
}