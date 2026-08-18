package com.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeLeaveMyEntitlement_Page extends BasePage {

    Actions actions;
    JavascriptExecutor js;
    WebDriverWait wait;

    public EmployeeLeaveMyEntitlement_Page() {
        super();

        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // =========================
    // Leave Menu
    // =========================

    @FindBy(xpath = "//span[text()='Leave']")
    WebElement leaveMenu;

    @FindBy(xpath = "//span[contains(@class,'oxd-topbar-body-nav-tab-item') and normalize-space()='Entitlements']")
    WebElement entitlementsMenu;

    @FindBy(xpath = "//a[@role='menuitem' and normalize-space()='My Entitlements']")
    WebElement myEntitlementsSubMenu;

    // =========================
    // Leave Type & Leave Period
    // =========================

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    WebElement leaveTypeDropdown;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
    WebElement leavePeriodDropdown;

    // =========================
    // Search
    // =========================

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    // =========================
    // Result Messages
    // =========================

    @FindBy(xpath = "//*[contains(normalize-space(),'Records Found')]")
    WebElement recordsFoundText;

    @FindBy(xpath = "//span[normalize-space()='No Records Found']")
    WebElement noRecordsFoundText;

    @FindBy(xpath = "//*[contains(normalize-space(),'Total') and contains(normalize-space(),'Day')]")
    WebElement totalDaysText;

    // =========================
    // Navigation
    // =========================

    public void clickByJS(WebElement element) {

        helper.waitForElement(element);

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        js.executeScript(
                "arguments[0].click();",
                element
        );
    }

    public void clickLeaveMenu() {

        clickByJS(leaveMenu);
    }

    public void clickEntitlementsMenu() {

        helper.waitForElement(entitlementsMenu);

        actions.moveToElement(entitlementsMenu).perform();

        clickByJS(entitlementsMenu);
    }

    public void clickMyEntitlementsSubMenu() {

        clickByJS(myEntitlementsSubMenu);
    }

    public void navigateToLeaveEntitlementsPage() {

        clickLeaveMenu();

        clickEntitlementsMenu();

        clickMyEntitlementsSubMenu();
    }

    // =========================
    // Leave Type
    // =========================

    public void clickLeaveTypeDropdown() {

        clickByJS(leaveTypeDropdown);
    }

    public void selectLeaveType(String leaveType) {

        clickLeaveTypeDropdown();

        if (leaveType.equalsIgnoreCase("CAN - Personal")) {

            helper.pressDownAndEnter(1);

        } else if (leaveType.equalsIgnoreCase("CAN - Vacation")) {

            helper.pressDownAndEnter(2);

        } else {

            throw new IllegalArgumentException(
                    "Unsupported leave type: " + leaveType
            );
        }
    }

    // =========================
    // Leave Period
    // =========================

    public void clickLeavePeriodDropdown() {

        clickByJS(leavePeriodDropdown);
    }

    public void selectLeavePeriod(String leavePeriod) {

        clickLeavePeriodDropdown();

        /*
         * Leave period dropdown starts from the first year.
         *
         * Example:
         *
         * 2020 -> HOME + 0 DOWN
         * 2021 -> HOME + 1 DOWN
         * 2022 -> HOME + 2 DOWN
         * 2023 -> HOME + 3 DOWN
         * 2024 -> HOME + 4 DOWN
         * 2025 -> HOME + 5 DOWN
         * 2026 -> HOME + 6 DOWN
         * 2027 -> HOME + 7 DOWN
         *
         * HOME is important because 2026 is selected by default
         * in the Jenkins environment.
         */

        int year;

        try {

            year = Integer.parseInt(
                    leavePeriod.substring(0, 4)
            );

        } catch (Exception e) {

            throw new IllegalArgumentException(
                    "Invalid leave period format: " + leavePeriod
            );
        }

        int firstYear = 2020;

        int downCount = year - firstYear;

        if (downCount < 0 || downCount > 7) {

            throw new IllegalArgumentException(
                    "Unsupported leave period: " + leavePeriod
            );
        }

        /*
         * Move to the first option.
         *
         * This avoids depending on the currently selected year.
         */
        actions.sendKeys(Keys.HOME).perform();

        /*
         * Move to required year.
         */
        for (int i = 0; i < downCount; i++) {

            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }

        /*
         * Select the highlighted option.
         */
        actions.sendKeys(Keys.ENTER).perform();

        /*
         * Verify that correct period was selected.
         */
        verifySelectedLeavePeriod(leavePeriod);
    }

    // =========================
    // Verify Selected Leave Period
    // =========================

    public void verifySelectedLeavePeriod(String expectedLeavePeriod) {

        helper.waitForElement(leavePeriodDropdown);

        String actualLeavePeriod = leavePeriodDropdown
                .getText()
                .trim();

        System.out.println(
                "Expected Leave Period: "
                        + expectedLeavePeriod
        );

        System.out.println(
                "Actual Leave Period: "
                        + actualLeavePeriod
        );

        if (!actualLeavePeriod.equalsIgnoreCase(expectedLeavePeriod)) {

            throw new AssertionError(
                    "Leave Period mismatch. Expected: "
                            + expectedLeavePeriod
                            + " but actual: "
                            + actualLeavePeriod
            );
        }
    }

    // =========================
    // Search
    // =========================

    public void clickSearchButton() {

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                searchButton
        );

        helper.waitForElementToBeClickable(searchButton);

        js.executeScript(
                "arguments[0].click();",
                searchButton
        );
    }

    // =========================
    // Records Found
    // =========================

    public boolean isRecordsFoundDisplayed() {

        helper.waitForElement(recordsFoundText);

        return recordsFoundText.isDisplayed();
    }

    // =========================
    // No Records Found
    // =========================

    public boolean isNoRecordsFoundDisplayed() {

        helper.waitForElement(noRecordsFoundText);

        return noRecordsFoundText.isDisplayed();
    }

    // =========================
    // Total Days
    // =========================

    public boolean isTotalDaysDisplayed() {

        helper.waitForElement(totalDaysText);

        return totalDaysText.isDisplayed();
    }

    public String getTotalDaysText() {

        helper.waitForElement(totalDaysText);

        return totalDaysText.getText();
    }
}
