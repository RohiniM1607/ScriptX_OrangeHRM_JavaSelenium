package com.actions;

import com.hooks.Hooks;
import com.pages.CreateUserCredentialsPage;

public class CreateUserCredentialsActions {

    CreateUserCredentialsPage createUserPage;

    public CreateUserCredentialsActions() {
        createUserPage = new CreateUserCredentialsPage(Hooks.driver);
    }

    public void navigateToAdminUserManagementPage() {
        createUserPage.navigateToAdmin();
    }

    public void clickAddButton() {
        createUserPage.clickAddBtn();
    }

    public boolean verifyAddUserPageDisplayed() {
        return createUserPage.isAddUserPageDisplayed();
    }

    public void enterUserCredentialDetails(String role, String employeeName, String status,
                                           String username, String password, String confirmPassword) {

        createUserPage.selectUserRole(role);
        createUserPage.enterEmployeeName(employeeName);
        createUserPage.selectStatus(status);

        String uniqueUsername = username + System.currentTimeMillis();

        createUserPage.enterUsername(uniqueUsername);
        createUserPage.enterPassword(password);
        createUserPage.enterConfirmPassword(confirmPassword);
    }

    public void clickSaveButton() {
        createUserPage.clickSaveButton();
    }

    public boolean verifySuccessMessageDisplayed() {
        return createUserPage.isSuccessMessageDisplayed();
    }
}