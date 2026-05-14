package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utilities.HelperClass;

public class LoginPage extends BasePage {

	HelperClass helper = new HelperClass(driver);

	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginBtn;

	public void enterUsername(String uname) {
		helper.enterText(username, uname);
	}

	public void enterPassword(String pwd) {
		helper.enterText(password, pwd);
	}

	public void clickLoginButton() {
		helper.clickElement(loginBtn);
	}

}
