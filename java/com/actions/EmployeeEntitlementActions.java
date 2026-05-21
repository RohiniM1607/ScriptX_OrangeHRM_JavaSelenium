package com.actions;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.pages.EmployeeEntitlementPage;
import com.utilities.CSVReader;
import com.utilities.ConfigReader;
import com.utilities.DP_Excel;

import io.cucumber.datatable.DataTable;

public class EmployeeEntitlementActions {

    private static final Logger logger = LogManager.getLogger(EmployeeEntitlementActions.class);

    EmployeeEntitlementPage employeeEntitlementPage;
    ConfigReader entitlementReader = new ConfigReader("testData.properties");

    public EmployeeEntitlementActions() {
        employeeEntitlementPage = new EmployeeEntitlementPage();
    }

    public void navigateToEmployeeEntitlementsPage() {

        logger.info("Navigating to Employee Entitlements page");

        try {
            employeeEntitlementPage.navigateToLeaveEntitlementsPage();
        } 
        catch (Exception e) {
            logger.error("Failed to navigate to Employee Entitlements page", e);
            throw e;
        }
    }

    public boolean verifyEmployeeEntitlementsPageDisplayed() {

        logger.info("Verifying Employee Entitlements page is displayed");
        return employeeEntitlementPage.isEmployeeEntitlementsPageDisplayed();
    }

    public void searchEmployeeEntitlement(DataTable dataTable) throws IOException {

        logger.info("Searching employee entitlement using Property File, DataTable and CSV");

        List<String> fields = dataTable.asList();

        String employeeName = "";
        String leaveType = "";
        String leavePeriod = "";

        if (fields.contains("employeeName")) {
            employeeName = entitlementReader.getData("entitlementEmployeeName");
        }

        if (fields.contains("leaveType")) {
            leaveType = entitlementReader.getData("leaveType");
        }

        if (fields.contains("leavePeriod")) {
            leavePeriod = entitlementReader.getData("leavePeriod");
        }

        CSVReader csvReader = new CSVReader();
        String[] csvData = csvReader.getCSVData("src/test/resources/testdata/EmployeeEntitlement.csv");

        if (employeeName == null || employeeName.trim().isEmpty()) {
            employeeName = csvData[0].trim();
        }

        if (leaveType == null || leaveType.trim().isEmpty()) {
            leaveType = csvData[1].trim();
        }

        if (leavePeriod == null || leavePeriod.trim().isEmpty()) {
            leavePeriod = csvData[2].trim();
        }

        logger.info("Employee Name: " + employeeName);
        logger.info("Leave Type: " + leaveType);
        logger.info("Leave Period: " + leavePeriod);

        employeeEntitlementPage.enterEmployeeName(employeeName);
        employeeEntitlementPage.selectLeaveType(leaveType);
        employeeEntitlementPage.selectLeavePeriod(leavePeriod);
        employeeEntitlementPage.clickSearchButton();
    }

    public void searchWithInvalidEmployeeName(String employeeName) {

        logger.info("Searching entitlement with invalid employee name: " + employeeName);

        String leaveType = entitlementReader.getData("leaveType");
        String leavePeriod = entitlementReader.getData("leavePeriod");

        employeeEntitlementPage.enterInvalidEmployeeName(employeeName);

        if (leaveType != null && !leaveType.trim().isEmpty()) {
            employeeEntitlementPage.selectLeaveType(leaveType);
        }

        if (leavePeriod != null && !leavePeriod.trim().isEmpty()) {
            employeeEntitlementPage.selectLeavePeriod(leavePeriod);
        }

        employeeEntitlementPage.clickSearchButton();
    }

    public void searchWithoutEmployeeName() throws IOException {

        logger.info("Searching entitlement without employee name using Excel data");

        DP_Excel excel = new DP_Excel();

        Object[][] data = excel.getExcelData(
                "src/test/resources/testdata/EmployeeEntitlement.xlsx",
                "Sheet1"
        );

        String leaveType = getCellValue(data[0][1]);
        String leavePeriod = getCellValue(data[0][2]);

        logger.info("Leave Type from Excel: " + leaveType);
        logger.info("Leave Period from Excel: " + leavePeriod);

        if (leaveType != null && !leaveType.trim().isEmpty()) {
            employeeEntitlementPage.selectLeaveType(leaveType);
        }

        if (leavePeriod != null && !leavePeriod.trim().isEmpty()) {
            employeeEntitlementPage.selectLeavePeriod(leavePeriod);
        }

        employeeEntitlementPage.clickSearchButton();

        String actualMessage = employeeEntitlementPage.getEmployeeNameRequiredMessage();

        logger.info("Actual validation message: " + actualMessage);

        Assert.assertEquals(actualMessage, "Required", "Employee name required validation message is not displayed");
    }

    public boolean verifySearchResultDisplayed() {

        logger.info("Verifying employee entitlement search result is displayed");
        return employeeEntitlementPage.isSearchResultDisplayed();
    }

    public boolean verifyInvalidEmployeeValidationDisplayed() {

        logger.info("Verifying invalid employee validation message is displayed");
        return employeeEntitlementPage.isInvalidValidationDisplayed();
    }

    public boolean verifyRequiredEmployeeNameValidationDisplayed() {

        logger.info("Verifying required employee name validation message is displayed");
        return employeeEntitlementPage.isRequiredValidationDisplayed();
    }

    public String getCellValue(Object value) {

        if (value == null) {
            return "";
        }

        return value.toString().trim();
    }
}