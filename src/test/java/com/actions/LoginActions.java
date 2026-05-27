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

	public void enterInvalidCredentials() {

		String username = testData.getData("invalid_username");
		String password = testData.getData("invalid_password");

		lp.enterUsername(username);
		lp.enterPassword(password);
	}

	public void enterEmptyCredentials() {

		String username = testData.getData("empty_username");
		String password = testData.getData("empty_password");

		lp.enterUsername(username);
		lp.enterPassword(password);
	}

	public void enterValidUsernameInvalidPassword() {

		String username = testData.getData("username");
		String password = testData.getData("invalid_password");

		lp.enterUsername(username);
		lp.enterPassword(password);
	}

	public void enterInvalidUsernameValidPassword() {

		String username = testData.getData("invalid_username");
		String password = testData.getData("password");

		lp.enterUsername(username);
		lp.enterPassword(password);
	}

	public void enterEmployeeCredentials() {

		String username = testData.getData("employee_username");
		String password = testData.getData("employee_password");

		lp.enterUsername(username);
		lp.enterPassword(password);
	}

	public void clickLogin() {
		lp.clickLoginButton();
	}

	public String getErrorMessage() {
		return lp.getErrorMessage();
	}

	public void logout() {
		lp.clickProfileDropdown();
		lp.clickLogout();
	}
}