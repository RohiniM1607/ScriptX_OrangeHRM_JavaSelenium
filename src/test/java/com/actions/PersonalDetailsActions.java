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
    HelperClass helper=new HelperClass();

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

    public String successisDisplayed() {
        WebDriverWait MWait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(15));
        WebElement msg = MWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast-content')]//p[1]")));
        return msg.getText().trim();
    }

	public boolean SuccesssisnotDisplayed() {
    try {
        WebDriverWait MesWait = new WebDriverWait(helper.driver.get(), Duration.ofSeconds(5));
        MesWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast-content')]//p[1]")));
        return true;
    } catch (Exception e) {
        return false;
    }
}
}