package com.actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pages.DashBoardEmployeePage;
import com.utilities.HelperClass;

public class DashBoardActions {

    DashBoardEmployeePage dashBoardPage;
    WebDriverWait wait;
    HelperClass helper = new HelperClass();

    public DashBoardActions() {
        dashBoardPage = new DashBoardEmployeePage();
        wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(60));
        // ↑ Changed from 20 to 60 — demo site is slow, 20s is not enough
    }

    public boolean isDashboardDisplayed() {
        // PRIMARY CHECK: URL-based — does not depend on any XPath at all.
        // After successful login OrangeHRM always redirects to a URL
        // containing "dashboard". This is the most reliable check.
        WebDriverWait dashWait = new WebDriverWait(helper.getDriver(), 
                                                    Duration.ofSeconds(60));
        dashWait.until(ExpectedConditions.urlContains("dashboard"));

        // SECONDARY CHECK: verify at least one known post-login element
        // exists so we know the page actually rendered, not just redirected.
        dashWait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[normalize-space()='My Info']")),
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[normalize-space()='Dashboard']")),
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[normalize-space()='Dashboard']"))
        ));

        return true;
    }

    public void navigateToMyInfo() {
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.menuMyInfo));
        dashBoardPage.menuMyInfo.click();
    }

    public void navigateToProfilePicture() {
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.menuMyInfo));
        dashBoardPage.menuMyInfo.click();
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.Profile));
        dashBoardPage.Profile.click();
    }

    public void navigateToContactDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.menuMyInfo));
        dashBoardPage.menuMyInfo.click();
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.menuContactDetails));
        dashBoardPage.menuContactDetails.click();
    }

    public void navigateToEmergencyContacts() {
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.menuMyInfo));
        dashBoardPage.menuMyInfo.click();
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.menuEmergencyContacts));
        dashBoardPage.menuEmergencyContacts.click();
    }

    public void navigateToDependents() {
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.menuMyInfo));
        dashBoardPage.menuMyInfo.click();
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.menuDependents));
        dashBoardPage.menuDependents.click();
    }
    
    public void navigateToSalary() {
    	wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.menuMyInfo));
        dashBoardPage.menuMyInfo.click();
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.menuSalary));
        dashBoardPage.menuSalary.click();
    }
}