package com.actions;

import java.io.File;
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

        // Fix 4: .clear() does not trigger React's onChange on OrangeHRM inputs.
        // Use CTRL+A + DELETE instead — same pattern used in all other actions.
        dependentsPage.nameInput.click();
        dependentsPage.nameInput.sendKeys(Keys.CONTROL + "a");
        dependentsPage.nameInput.sendKeys(Keys.DELETE);
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
        // Tab commits the date value in OrangeHRM's custom date component
        dependentsPage.dateOfBirth.sendKeys(Keys.TAB);
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

    public void uploadAttachment(String fileName) {
        // Fix 1a: accept fileName as parameter — don't hardcode it
        // Fix 1b: use File.separator instead of \\ — works on all OS
        String filePath = System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "test"
                + File.separator + "resources"
                + File.separator + fileName;

        WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        fileInput.sendKeys(filePath);

        // Fix 1c: browsers block JS from reading file input value — never
        // wait for filename text in DOM. Wait for Save button clickability
        // instead — that confirms the form accepted the file.
        wait.until(ExpectedConditions.elementToBeClickable(dependentsPage.saveButton1));
    }

    public void uploadInvalidAttachment(String fileName) {
        // Fix 2: use the fileName parameter — was hardcoded as Large_file.txt
        // but the actual file on disk is Large_file.pdf
        String filePath = System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "test"
                + File.separator + "resources"
                + File.separator + fileName;

        WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        fileInput.sendKeys(filePath);

        // Wait for OrangeHRM's size error message to appear
        wait.until(ExpectedConditions.visibilityOf(dependentsPage.fileSizeErrorMessage));
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