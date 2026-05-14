package com.actions;

import com.pages.LoginPage;
import com.utilities.ConfigReader;

public class LoginActions extends BaseActions {

	LoginPage lp = new LoginPage();
	ConfigReader testData = new ConfigReader("testData.properties");

	public void enterValidCredentials() {

		String username = testData.getData("username");
		String password = testData.getData("password");

		lp.enterUsername(username);
		lp.enterPassword(password);
	}

	public void clickLogin() {
		lp.clickLoginButton();
	}
}