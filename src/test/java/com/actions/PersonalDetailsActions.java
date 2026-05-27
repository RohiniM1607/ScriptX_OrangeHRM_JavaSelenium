package com.actions;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.pages.PersonalDetailsPage;
import com.utilities.HelperClass;

public class PersonalDetailsActions {

    PersonalDetailsPage personalDetailsPage = null;
    WebDriverWait wait;
    HelperClass helper = new HelperClass();

    public PersonalDetailsActions() {
        this.personalDetailsPage = new PersonalDetailsPage();
        wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(30));
    }

    public void updatePersonalDetails(List<Map<String, String>> data) {
        Map<String, String> row = data.get(0);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-loading-spinner-container")));

        wait.until(ExpectedConditions.visibilityOf(personalDetailsPage.LicenseExpiryDate));
        personalDetailsPage.LicenseExpiryDate.click();
        personalDetailsPage.LicenseExpiryDate.sendKeys(Keys.CONTROL + "a");
        personalDetailsPage.LicenseExpiryDate.sendKeys(Keys.DELETE);
        personalDetailsPage.LicenseExpiryDate.sendKeys(row.get("LicenseExpiryDate"));

        personalDetailsPage.Nationality.click();
        wait.until(ExpectedConditions.visibilityOf(personalDetailsPage.dropdownListbox));
        List<WebElement> nationalityOptions = wait.until(ExpectedConditions.visibilityOfAllElements(personalDetailsPage.dropdownOptions));
        for (WebElement option : nationalityOptions) {
            if (option.getText().trim().equalsIgnoreCase(row.get("Nationality"))) {
                option.click();
                break;
            }
        }

        personalDetailsPage.MaritalStatus.click();
        wait.until(ExpectedConditions.visibilityOf(personalDetailsPage.dropdownListbox));
        List<WebElement> maritalOptions = wait.until(ExpectedConditions.visibilityOfAllElements(personalDetailsPage.dropdownOptions));
        for (WebElement option : maritalOptions) {
            if (option.getText().trim().equalsIgnoreCase(row.get("MaritalStatus"))) {
                option.click();
                break;
            }
        }

        if (row.get("Gender").equalsIgnoreCase("Female")) {
            personalDetailsPage.radioFemale.click();
        }

        personalDetailsPage.BloodType.click();
        wait.until(ExpectedConditions.visibilityOf(personalDetailsPage.dropdownListbox));
        List<WebElement> bloodTypeOptions = wait.until(ExpectedConditions.visibilityOfAllElements(personalDetailsPage.dropdownOptions));
        for (WebElement option : bloodTypeOptions) {
            if (option.getText().trim().equalsIgnoreCase(row.get("BloodType"))) {
                option.click();
                break;
            }
        }

        personalDetailsPage.TestField.clear();
        personalDetailsPage.TestField.sendKeys(row.get("TestField"));
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(personalDetailsPage.Pdsave));
        personalDetailsPage.Pdsave.click();
    }

    public String successisDisplayed() {
        WebDriverWait mWait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(15));
        WebElement msg = mWait.until(ExpectedConditions.visibilityOf(personalDetailsPage.Pdsuccess));
        return msg.getText().trim();
    }

    public boolean SuccesssisnotDisplayed() {
        try {
            WebDriverWait mesWait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(5));
            mesWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast-content')]//p[1]")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void uploadProfilePicture(String relativePath) {
        String absolutePath = System.getProperty("user.dir") + relativePath;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        personalDetailsPage.fileinput.sendKeys(absolutePath);
    }

    public void clickSave1() {
        wait.until(ExpectedConditions.elementToBeClickable(personalDetailsPage.Ppsave));
        personalDetailsPage.Ppsave.click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(
                personalDetailsPage.Ppsuccess)).getText().trim();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast-content')]//p[1]")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}