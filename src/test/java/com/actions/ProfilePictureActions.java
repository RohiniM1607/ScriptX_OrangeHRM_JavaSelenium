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
    HelperClass helper = new HelperClass();

    public ProfilePictureActions() {
        this.profilePicturePage = new ProfilePicturePage();
        wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(15));
    }

    public void uploadProfilePicture(String relativePath) {
        String absolutePath = System.getProperty("user.dir") + relativePath;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        profilePicturePage.fileInput.sendKeys(absolutePath);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(profilePicturePage.btnSave));
        profilePicturePage.btnSave.click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(profilePicturePage.txtSuccessMessage))
                .getText().trim();
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