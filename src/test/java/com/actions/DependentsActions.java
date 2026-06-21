package com.actions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pages.DependentsPage;
import com.utilities.HelperClass;

public class DependentsActions {

    DependentsPage dependentsPage;
    WebDriverWait wait;
    HelperClass helper = new HelperClass();

    public DependentsActions() {
        dependentsPage = new DependentsPage();
        wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(30));
    }

    public void clickAddIcon() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.oxd-loading-spinner-container")));
        wait.until(ExpectedConditions.elementToBeClickable(dependentsPage.addIcon));
        dependentsPage.addIcon.click();
    }

    public void fillDependentDetails(String name, String relationship, String dateOfBirth) {

        wait.until(ExpectedConditions.visibilityOf(dependentsPage.nameInput));
        dependentsPage.nameInput.clear();
        dependentsPage.nameInput.sendKeys(name);

        dependentsPage.relationshipDropdown.click();

        wait.until(ExpectedConditions.visibilityOf(dependentsPage.dropdownListbox));
        List<WebElement> options = wait.until(
                ExpectedConditions.visibilityOfAllElements(dependentsPage.dropdownOptions));

        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(relationship)) {
                option.click();
                break;
            }
        }

        dependentsPage.dateOfBirth.click();
        dependentsPage.dateOfBirth.sendKeys(Keys.CONTROL + "a");
        dependentsPage.dateOfBirth.sendKeys(Keys.DELETE);
        dependentsPage.dateOfBirth.sendKeys(dateOfBirth);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(dependentsPage.saveButton));
        dependentsPage.saveButton.click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(dependentsPage.successMessage))
                .getText().trim();
    }

   
    public void clickAttachmentAddButton() {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.cssSelector("div.oxd-loading-spinner-container")));

        wait.until(ExpectedConditions.visibilityOf(dependentsPage.attachmentAddButton));

        wait.until(ExpectedConditions.elementToBeClickable(dependentsPage.attachmentAddButton));

        dependentsPage.attachmentAddButton.click();
    }

    public void uploadAttachment() {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testfile1.txt";

        WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));

        fileInput.sendKeys(filePath);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'testfile2.txt')]")));
    }

    public void uploadInvalidAttachment() {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\Large_file.txt";

        WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));

        fileInput.sendKeys(filePath);
    }

    public void clickSaveAttachment() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.cssSelector("div.oxd-loading-spinner-container")));
        wait.until(ExpectedConditions.elementToBeClickable(dependentsPage.saveButton1));
        dependentsPage.saveButton1.click();
    }

    public String getAttachmentSuccessMessage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class,'oxd-toast')]//p")))
            .getText().trim();
    }

    public String getFileSizeErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(dependentsPage.fileSizeErrorMessage))
                .getText().trim();
    }
}