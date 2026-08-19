package com.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pages.SalaryPage;
import com.utilities.HelperClass;

public class SalaryActions {

    SalaryPage salaryPage;
    WebDriverWait wait;
    HelperClass helper = new HelperClass();

    public SalaryActions() {
        salaryPage = new SalaryPage();
        wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(30));
    }

    // Reads salary_data.csv and returns all rows as list of maps
    public List<Map<String, String>> readSalaryCSV() {
        List<Map<String, String>> records = new ArrayList<>();
        String filePath = System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "test"
                + File.separator + "resources"
                + File.separator + "testdata"
                + File.separator + "SalaryData.csv";

        try (BufferedReader br = new BufferedReader(
                new java.io.InputStreamReader(
                    new java.io.FileInputStream(filePath), "UTF-8"))) {

            String headerLine = br.readLine();
            if (headerLine == null) return records;

            // Fix 1a: Strip BOM character if present (common in Windows-saved CSV files)
            // BOM is an invisible character at the start of UTF-8 files saved by Excel/Notepad
            headerLine = headerLine.replace("\uFEFF", "").trim();

            String[] headers = headerLine.split(",");

            // Trim each header to remove any hidden whitespace
            for (int i = 0; i < headers.length; i++) {
                headers[i] = headers[i].trim();
            }

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // skip blank lines

                // Handle quoted values containing commas
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    String value = i < values.length ? values[i].trim().replace("\"", "") : "";
                    row.put(headers[i], value);
                }
                records.add(row);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read salary CSV at: " + filePath, e);
        }
        return records;
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.oxd-loading-spinner-container")));
        wait.until(ExpectedConditions.elementToBeClickable(salaryPage.addButton));
        salaryPage.addButton.click();
    }

    private void selectFromDropdown(WebElement dropdown, String value) {
        if (value == null || value.isEmpty()) return;
        wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();
        wait.until(ExpectedConditions.visibilityOf(salaryPage.dropdownListbox));
        List<WebElement> options = wait.until(
                ExpectedConditions.visibilityOfAllElements(salaryPage.dropdownOptions));
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(value)) {
                option.click();
                return;
            }
        }
        throw new RuntimeException("Dropdown option not found: " + value);
    }

    public void fillSalaryDetails(Map<String, String> data) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.oxd-loading-spinner-container")));

        // Salary Component — required text input
        wait.until(ExpectedConditions.visibilityOf(salaryPage.salaryComponentInput));
        salaryPage.salaryComponentInput.click();
        salaryPage.salaryComponentInput.sendKeys(Keys.CONTROL + "a");
        salaryPage.salaryComponentInput.sendKeys(Keys.DELETE);
        salaryPage.salaryComponentInput.sendKeys(data.get("SalaryComponent"));

        // Pay Grade — optional dropdown
        selectFromDropdown(salaryPage.payGradeDropdown, data.get("PayGrade"));

        // Pay Frequency — optional dropdown
        // Re-wait because Pay Grade selection may trigger DOM refresh
        wait.until(ExpectedConditions.elementToBeClickable(salaryPage.payFrequencyDropdown));
        selectFromDropdown(salaryPage.payFrequencyDropdown, data.get("PayFrequency"));

        // Currency — required dropdown
        wait.until(ExpectedConditions.elementToBeClickable(salaryPage.currencyDropdown));
        selectFromDropdown(salaryPage.currencyDropdown, data.get("Currency"));

        // Amount — required text input
        wait.until(ExpectedConditions.visibilityOf(salaryPage.amountInput));
        salaryPage.amountInput.click();
        salaryPage.amountInput.sendKeys(Keys.CONTROL + "a");
        salaryPage.amountInput.sendKeys(Keys.DELETE);
        salaryPage.amountInput.sendKeys(data.get("Amount"));

        // Comments — optional textarea
        wait.until(ExpectedConditions.visibilityOf(salaryPage.commentsInput));
        salaryPage.commentsInput.click();
        salaryPage.commentsInput.sendKeys(Keys.CONTROL + "a");
        salaryPage.commentsInput.sendKeys(Keys.DELETE);
        salaryPage.commentsInput.sendKeys(data.get("Comments"));
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(salaryPage.saveButton));
        salaryPage.saveButton.click();
    }

    public String getSuccessMessage() {
        WebDriverWait msgWait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(15));
        return msgWait.until(ExpectedConditions.visibilityOf(salaryPage.successMessage))
                .getText().trim();
    }
}