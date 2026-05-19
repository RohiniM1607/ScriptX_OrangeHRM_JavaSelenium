package com.actions;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pages.ContactDetailsPage;
import com.utilities.HelperClass;
import com.utilities.ConfigReader;

public class ContactDetailsActions {

    ContactDetailsPage contactDetailsPage = null;
    WebDriverWait wait;
    HelperClass helper = new HelperClass();
    ConfigReader testData = new ConfigReader("testData.properties");

    public ContactDetailsActions() {
        this.contactDetailsPage = new ContactDetailsPage();
        wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(30));
    }

    public void updateContactDetails() {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.oxd-loading-spinner-container")));

        wait.until(ExpectedConditions.visibilityOf(contactDetailsPage.street1));

        contactDetailsPage.street1.click();
        contactDetailsPage.street1.sendKeys(Keys.CONTROL + "a");
        contactDetailsPage.street1.sendKeys(Keys.DELETE);
        contactDetailsPage.street1.sendKeys(testData.getData("street1"));

        contactDetailsPage.street2.click();
        contactDetailsPage.street2.sendKeys(Keys.CONTROL + "a");
        contactDetailsPage.street2.sendKeys(Keys.DELETE);
        contactDetailsPage.street2.sendKeys(testData.getData("street2"));

        contactDetailsPage.city.click();
        contactDetailsPage.city.sendKeys(Keys.CONTROL + "a");
        contactDetailsPage.city.sendKeys(Keys.DELETE);
        contactDetailsPage.city.sendKeys(testData.getData("city"));

        contactDetailsPage.state.click();
        contactDetailsPage.state.sendKeys(Keys.CONTROL + "a");
        contactDetailsPage.state.sendKeys(Keys.DELETE);
        contactDetailsPage.state.sendKeys(testData.getData("state"));

        contactDetailsPage.zipCode.click();
        contactDetailsPage.zipCode.sendKeys(Keys.CONTROL + "a");
        contactDetailsPage.zipCode.sendKeys(Keys.DELETE);
        contactDetailsPage.zipCode.sendKeys(testData.getData("zip"));

        contactDetailsPage.country.click();
        selectDropdown(testData.getData("country"));

        contactDetailsPage.homeTelephone.click();
        contactDetailsPage.homeTelephone.sendKeys(Keys.CONTROL + "a");
        contactDetailsPage.homeTelephone.sendKeys(Keys.DELETE);
        contactDetailsPage.homeTelephone.sendKeys(testData.getData("homeTelephone"));

        contactDetailsPage.mobile.click();
        contactDetailsPage.mobile.sendKeys(Keys.CONTROL + "a");
        contactDetailsPage.mobile.sendKeys(Keys.DELETE);
        contactDetailsPage.mobile.sendKeys(testData.getData("mobile"));

        contactDetailsPage.workTelephone.click();
        contactDetailsPage.workTelephone.sendKeys(Keys.CONTROL + "a");
        contactDetailsPage.workTelephone.sendKeys(Keys.DELETE);
        contactDetailsPage.workTelephone.sendKeys(testData.getData("workTelephone"));

        contactDetailsPage.workEmail.click();
        contactDetailsPage.workEmail.sendKeys(Keys.CONTROL + "a");
        contactDetailsPage.workEmail.sendKeys(Keys.DELETE);
        contactDetailsPage.workEmail.sendKeys(testData.getData("workEmail"));
    }

    public void selectDropdown(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[text()='" + value + "']")))
                .click();
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(contactDetailsPage.btnSave));
        contactDetailsPage.btnSave.click();
    }

    public void clickAddAttachment() {

        wait.until(ExpectedConditions.elementToBeClickable(contactDetailsPage.addIcon));
        contactDetailsPage.addIcon.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
    }

    public void uploadAttachment() {

        String filePath = System.getProperty("user.dir")+ "\\src\\test\\resources\\testfile.txt";
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        fileInput.sendKeys(filePath);
        wait.until(ExpectedConditions.attributeContains(fileInput, "value", "testfile.txt"));
    }

    public void clickSaveAttachment() {
        wait.until(ExpectedConditions.elementToBeClickable(contactDetailsPage.btnAttachmentSave));
        contactDetailsPage.btnAttachmentSave.click();
    }

    public String getSuccessMessage() {
        WebDriverWait msgWait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(15));

        WebElement msg = msgWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-toast-content')]//p[1]")));

        return msg.getText().trim();
    }
}