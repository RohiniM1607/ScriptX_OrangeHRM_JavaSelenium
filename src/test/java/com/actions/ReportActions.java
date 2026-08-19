package com.actions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pages.ReportPage;
import com.utilities.HelperClass;

public class ReportActions {

    ReportPage reportToPage;
    WebDriverWait wait;
    HelperClass helper = new HelperClass();

    public ReportActions() {
        reportToPage = new ReportPage();
        wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(30));
    }

    // ── Supervisor ──────────────────────────────────────────────────────

    public void clickAddSupervisor() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.oxd-loading-spinner-container")));
        wait.until(ExpectedConditions.elementToBeClickable(
                reportToPage.addSupervisorButton));
        reportToPage.addSupervisorButton.click();
    }

    public void fillSupervisorDetails(String supervisorName, String reportingMethod) {
        // Type name into autocomplete input
        wait.until(ExpectedConditions.visibilityOf(reportToPage.supervisorNameInput));
        reportToPage.supervisorNameInput.click();
        reportToPage.supervisorNameInput.sendKeys(supervisorName);

        // Wait for autocomplete suggestions and pick matching option
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']")));
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElements(
                        reportToPage.autoCompleteOptions));
        for (WebElement suggestion : suggestions) {
            if (suggestion.getText().trim().equalsIgnoreCase(supervisorName)) {
                suggestion.click();
                break;
            }
        }

        // Select Reporting Method from dropdown
        selectReportingMethod(
                reportToPage.supervisorReportingMethodDropdown, reportingMethod);
    }

    public void clickSaveSupervisor() {
        wait.until(ExpectedConditions.elementToBeClickable(
                reportToPage.supervisorSaveButton));
        reportToPage.supervisorSaveButton.click();
    }

   
    // ── Shared ──────────────────────────────────────────────────────────

    private void selectReportingMethod(WebElement dropdown, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();
        wait.until(ExpectedConditions.visibilityOf(reportToPage.dropdownListbox));
        List<WebElement> options = wait.until(
                ExpectedConditions.visibilityOfAllElements(
                        reportToPage.dropdownOptions));
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(value)) {
                option.click();
                return;
            }
        }
        throw new RuntimeException("Reporting method not found in dropdown: " + value);
    }

    public String getSuccessMessage() {
        WebDriverWait msgWait = new WebDriverWait(helper.getDriver(),
                Duration.ofSeconds(15));
        return msgWait.until(
                ExpectedConditions.visibilityOf(reportToPage.successMessage))
                .getText().trim();
    }
}