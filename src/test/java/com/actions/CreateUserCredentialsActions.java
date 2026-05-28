package com.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.pages.CreateUserCredentialsPage;

public class CreateUserCredentialsActions {

	private static final Logger logger = LogManager.getLogger(CreateUserCredentialsActions.class);

	CreateUserCredentialsPage createUserPage;

	public CreateUserCredentialsActions() {
		createUserPage = new CreateUserCredentialsPage();
	}

	public void navigateToAdminUserManagementPage() {
		logger.info("Navigating to Admin User Management page");
		createUserPage.navigateToAdmin();
	}

	public void clickAddButton() {
		logger.info("Clicking Add button");
		createUserPage.clickAddBtn();
	}

	public boolean verifyAddUserPageDisplayed() {
		logger.info("Verifying Add User page is displayed");
		return createUserPage.isAddUserPageDisplayed();
	}

	public void enterUserCredentialDetails(String role, String employeeName, String status, String username,
			String password, String confirmPassword) {

		logger.info("Entering user credential details");

		if (role != null && !role.trim().isEmpty()) {
			logger.info("Role: " + role);
			createUserPage.selectUserRole(role);
		}

		if (employeeName != null && !employeeName.trim().isEmpty()) {
			logger.info("Employee Name: " + employeeName);
			createUserPage.enterEmployeeName(employeeName);
		}

		if (status != null && !status.trim().isEmpty()) {
			logger.info("Status: " + status);
			createUserPage.selectStatus(status);
		}

		if (username != null && !username.trim().isEmpty()) {
			String uniqueUsername = username + System.currentTimeMillis();
			logger.info("Generated unique username: " + uniqueUsername);
			createUserPage.enterUsername(uniqueUsername);
		}

		if (password != null && !password.trim().isEmpty()) {
			createUserPage.enterPassword(password);
		}

		if (confirmPassword != null && !confirmPassword.trim().isEmpty()) {
			createUserPage.enterConfirmPassword(confirmPassword);
		}
	}

	public void duplicateUserCredentialDetails(String role, String employeeName, String status, String username,
			String password, String confirmPassword) {

		logger.info("Entering user credential details");

		if (role != null && !role.trim().isEmpty()) {
			logger.info("Role: " + role);
			createUserPage.selectUserRole(role);
		}

		if (employeeName != null && !employeeName.trim().isEmpty()) {
			logger.info("Employee Name: " + employeeName);
			createUserPage.enterEmployeeName(employeeName);
		}

		if (status != null && !status.trim().isEmpty()) {
			logger.info("Status: " + status);
			createUserPage.selectStatus(status);
		}

		if (username != null && !username.trim().isEmpty()) {
			createUserPage.enterUsername(username);
		}

		if (password != null && !password.trim().isEmpty()) {
			createUserPage.enterPassword(password);
		}

		if (confirmPassword != null && !confirmPassword.trim().isEmpty()) {
			createUserPage.enterConfirmPassword(confirmPassword);
		}
	}

	public void clickSaveButton() {
		logger.info("Clicking Save button");
		createUserPage.clickSaveButton();
	}

	public boolean verifySuccessMessageDisplayed() {
		logger.info("Verifying success message is displayed");
		return createUserPage.isSuccessMessageDisplayed();
	}

	public boolean verifyRequiredValidationMessageDisplayed() {
		logger.info("Verifying required validation message is displayed");
		return createUserPage.isRequiredValidationMessageDisplayed();
	}

	public void verifyDuplicateUsernameValidationMessage() {
		Assert.assertTrue(createUserPage.isDuplicateUsernameValidationMessageDisplayed());
	}
}