package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utilities.HelperClass;

public class LoginPage extends BasePage {

	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginBtn;

	@FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
	WebElement errorMessage;

	@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
	WebElement profileDropdown;

	@FindBy(xpath = "//a[text()='Logout']")
	WebElement logoutBtn;

	public void enterUsername(String uname) {
		helper.enterText(username, uname);
	}

	public void enterPassword(String pwd) {
		helper.enterText(password, pwd);
	}

	public void clickLoginButton() {
		helper.clickElement(loginBtn);
	}

	public String getErrorMessage() {
		helper.waitForElement(errorMessage);
		return errorMessage.getText();
	}

	public void clickProfileDropdown() {
		helper.clickElement(profileDropdown);
	}

	public void clickLogout() {
		helper.clickElement(logoutBtn);
	}
}