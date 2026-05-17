package com.actions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hooks.Hooks;
import com.pages.PersonalDetailsPage;

public class PersonalDetailsActions {

    PersonalDetailsPage personalDetailsPage = null;
    WebDriverWait wait;

    public PersonalDetailsActions() {
        this.personalDetailsPage = new PersonalDetailsPage();
        wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
    }

    private void waitForLoaderToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
    }

    public void updatePersonalDetails(List<Map<String, String>> data) {

        Map<String, String> row = data.get(0);

        waitForLoaderToDisappear();

        wait.until(ExpectedConditions.visibilityOf(personalDetailsPage.LicenseExpiryDate));
        personalDetailsPage.LicenseExpiryDate.clear();
        personalDetailsPage.LicenseExpiryDate.sendKeys(row.get("LicenseExpiryDate"));

        personalDetailsPage.Nationality.click();
        selectDropdown(row.get("Nationality"));

        personalDetailsPage.MaritalStatus.click();
        selectDropdown(row.get("MaritalStatus"));

        if (row.get("Gender").equalsIgnoreCase("Female")) {
            personalDetailsPage.radioFemale.click();
        }

        personalDetailsPage.BloodType.click();
        selectDropdown(row.get("BloodType"));

        personalDetailsPage.TestField.clear();
        personalDetailsPage.TestField.sendKeys(row.get("TestField"));
    }

    public void selectDropdown(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']//span[text()='" + value + "']"))).click();
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(personalDetailsPage.btnSave));
        personalDetailsPage.btnSave.click();
    }

    public String getSuccessMessage() {
        WebDriverWait toastWait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(15));
        WebElement toast = toastWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast-content')]//p[1]"))
);
        return toast.getText().trim();
    }
}