package com.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pages.CreateUserCredentialsPage;
import com.utilities.HelperClass;

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

    public void enterUserCredentialDetails(String role, String employeeName, String status, String username, String password, String confirmPassword) {
    	logger.info("Entering user credential details");
        logger.info("Role: " + role);
        logger.info("Employee Name: " + employeeName);
        logger.info("Status: " + status);
        logger.info("Username: " + username);
        createUserPage.selectUserRole(role);
        createUserPage.enterEmployeeName(employeeName);
        createUserPage.selectStatus(status);

        String uniqueUsername = username + System.currentTimeMillis();
        logger.info("Generated unique username: " + uniqueUsername);
        createUserPage.enterUsername(uniqueUsername);
        createUserPage.enterPassword(password);
        createUserPage.enterConfirmPassword(confirmPassword);
    }

    public void clickSaveButton() {
    	logger.info("Clicking Save button");
        createUserPage.clickSaveButton();
    }

    public boolean verifySuccessMessageDisplayed() {
    	logger.info("Verifying success message is displayed");
        return createUserPage.isSuccessMessageDisplayed();
    }
}