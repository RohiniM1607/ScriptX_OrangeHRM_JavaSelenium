package com.actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pages.ProfilePicturePage;
import com.utilities.HelperClass;

public class ProfilePictureActions {

    ProfilePicturePage profilePicturePage;
    WebDriverWait wait;

    public ProfilePictureActions() {
        this.profilePicturePage = new ProfilePicturePage();
        wait = new WebDriverWait(HelperClass.driver, Duration.ofSeconds(10));
    }

    public void uploadProfilePicture(String filePath) {
        wait.until(ExpectedConditions.elementToBeClickable(profilePicturePage.addIcon));
        profilePicturePage.addIcon.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        ((JavascriptExecutor) HelperClass.driver).executeScript("arguments[0].style.display='block';", profilePicturePage.fileInput);
        profilePicturePage.fileInput.sendKeys(filePath);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(profilePicturePage.btnSave));
        profilePicturePage.btnSave.click();
    }

    public String getSuccessMessage() {
        WebDriverWait toastWait = new WebDriverWait(HelperClass.driver, Duration.ofSeconds(15));
        return toastWait.until(ExpectedConditions.visibilityOf(profilePicturePage.txtSuccessMessage)).getText().trim();
    }

}