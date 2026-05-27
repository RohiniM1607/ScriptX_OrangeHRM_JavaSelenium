package com.actions;

import com.pages.PIMPage;

public class PIMActions extends BaseActions {

    PIMPage pimPage = new PIMPage();

    public void navigateToAddEmployee() {

        pimPage.clickPIMMenu();

        pimPage.clickAddEmployee();
    }

    public void enterEmployeeDetails(
            String firstName,
            String lastName,
            String employeeId) {

        pimPage.enterFirstName(firstName);

        pimPage.enterLastName(lastName);

        pimPage.enterEmployeeId(employeeId);
    }

    public void clickSaveButton() {

        pimPage.clickSaveButton();

        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    public boolean verifyEmployeeCreated() {

        return pimPage.isPersonalDetailsDisplayed();
    }

    public boolean verifyRequiredMessage() {

        return pimPage.isRequiredMessageDisplayed();
    }

    public void navigateToSearchEmployee() {

        pimPage.clickPIMMenu();
    }

    public void searchEmployeeByName(String employeeName) {

        pimPage.enterSearchEmployeeName(employeeName);

        pimPage.clickSearchButton();
    }

    public void searchEmployeeById(String employeeId) {

        pimPage.enterSearchEmployeeId(employeeId);

        pimPage.clickSearchButton();
    }

    public boolean verifySearchSuccess() {

        return pimPage.isSearchResultDisplayed();
    }

    public boolean verifyNoRecordsFound() {

        return pimPage.isNoRecordFoundDisplayed();
    }
}
