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

    DependentsPage dependentsPage = null;
    WebDriverWait wait;
    HelperClass helper = new HelperClass();

    public DependentsActions() {
        this.dependentsPage = new DependentsPage();
        wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(30));
    }

    public void clickAddIcon() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-loading-spinner-container")));
        wait.until(ExpectedConditions.elementToBeClickable(dependentsPage.addIcon));
        dependentsPage.addIcon.click();
    }

    public void fillDependentDetails(String name, String relationship, String dateOfBirth) {
        wait.until(ExpectedConditions.visibilityOf(dependentsPage.nameInput));
        dependentsPage.nameInput.clear();
        dependentsPage.nameInput.sendKeys(name);

        dependentsPage.relationshipDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(dependentsPage.dropdownListbox));
        List<WebElement> relationshipOptions = wait.until(ExpectedConditions.visibilityOfAllElements(dependentsPage.dropdownOptions));
        for (WebElement option : relationshipOptions) {
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
        WebDriverWait msgWait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(15));
        return msgWait.until(ExpectedConditions.visibilityOf(dependentsPage.successMessage))
                .getText().trim();
    }
}