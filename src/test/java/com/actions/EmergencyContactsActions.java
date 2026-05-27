package com.actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pages.EmergencyContactsPage;
import com.utilities.HelperClass;

public class EmergencyContactsActions {

    EmergencyContactsPage emergencyContactsPage = null;
    WebDriverWait wait;
    HelperClass helper = new HelperClass();

    public EmergencyContactsActions() {
        this.emergencyContactsPage = new EmergencyContactsPage();
        wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(30));
    }

    public void clickAddEmergencyContact() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-loading-spinner-container")));
        wait.until(ExpectedConditions.elementToBeClickable(emergencyContactsPage.btnAddEmergencyContact));
        emergencyContactsPage.btnAddEmergencyContact.click();
    }

    public void fillEmergencyContactDetails(String name, String relationship,String homeTelephone, String mobile,String workTelephone) {
        wait.until(ExpectedConditions.visibilityOf(emergencyContactsPage.inputName));
        clearAndType(emergencyContactsPage.inputName, name);
        clearAndType(emergencyContactsPage.inputRelationship, relationship);
        clearAndType(emergencyContactsPage.inputHomeTelephone, homeTelephone);
        clearAndType(emergencyContactsPage.inputMobile, mobile);
        clearAndType(emergencyContactsPage.inputWorkTelephone, workTelephone);
    }

    public void clickSaveEmergencyContact() {
        wait.until(ExpectedConditions.elementToBeClickable(emergencyContactsPage.btnSaveEmergencyContact));
        emergencyContactsPage.btnSaveEmergencyContact.click();
    }

    public void clickAddAttachment() {
        wait.until(ExpectedConditions.elementToBeClickable(emergencyContactsPage.btnAddAttachment));
        emergencyContactsPage.btnAddAttachment.click();
    }

    public void uploadAttachment() {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testfile1.txt";
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        fileInput.sendKeys(filePath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'testfile1.txt')]")));
    }

    public void clickSaveAttachment() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(emergencyContactsPage.btnSaveAttachment));
        ((org.openqa.selenium.JavascriptExecutor) helper.getDriver()).executeScript("arguments[0].scrollIntoView(true);", saveBtn);
        wait.until(ExpectedConditions.elementToBeClickable(emergencyContactsPage.btnSaveAttachment));
        saveBtn.click();
    }

    public String getSuccessMessage() {
        WebDriverWait msgWait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(15));
        WebElement msg = msgWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast-content')]//p[1]")));
        return msg.getText().trim();
    }

    private void clearAndType(WebElement element, String value) {
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
    }
}