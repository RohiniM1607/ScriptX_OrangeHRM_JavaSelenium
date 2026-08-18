package com.actions;

import com.pages.EmployeeLeaveMyEntitlement_Page;

public class EmployeeLeaveMyEntitlement_Actions extends BaseActions {

    EmployeeLeaveMyEntitlement_Page employeeLeaveMyEntitlementPage = new EmployeeLeaveMyEntitlement_Page();

    public void clickLeaveMenu() {
        employeeLeaveMyEntitlementPage.clickLeaveMenu();
    }

    public void clickEntitlementsMenu() {
        employeeLeaveMyEntitlementPage.clickEntitlementsMenu();
    }

    public void clickMyEntitlementsSubMenu() {
        employeeLeaveMyEntitlementPage.clickMyEntitlementsSubMenu();
    }

    // Combined navigation method - keeps the 3 individual methods above
    // intact (still usable on their own), but bundles them for the
    // step definition to call as a single step.
    public void navigateToLeaveEntitlementsPage() {
        clickLeaveMenu();
        clickEntitlementsMenu();
        clickMyEntitlementsSubMenu();
    }

    public void selectLeaveType(String leaveType) {
        employeeLeaveMyEntitlementPage.selectLeaveType(leaveType);
    }

    public void selectLeavePeriod(String leavePeriod) {
        employeeLeaveMyEntitlementPage.selectLeavePeriod(leavePeriod);
    }

    public void clickSearchButton() {
        employeeLeaveMyEntitlementPage.clickSearchButton();
    }

    public boolean isRecordsFoundDisplayed() {
        return employeeLeaveMyEntitlementPage.isRecordsFoundDisplayed();
    }

    public boolean isNoRecordsFoundDisplayed() {
        return employeeLeaveMyEntitlementPage.isNoRecordsFoundDisplayed();
    }

    public boolean isTotalDaysDisplayed() {
        return employeeLeaveMyEntitlementPage.isTotalDaysDisplayed();
    }

    public String getTotalDaysText() {
        return employeeLeaveMyEntitlementPage.getTotalDaysText();
    }
}