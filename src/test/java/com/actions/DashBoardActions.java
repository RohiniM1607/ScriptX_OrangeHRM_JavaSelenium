package com.actions;

import java.time.Duration;

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
        wait = new WebDriverWait(helper.getDriver(), Duration.ofSeconds(20));
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

    public boolean isDashboardDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(dashBoardPage.dashboardHeader));
        return dashBoardPage.dashboardHeader.isDisplayed();
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
}