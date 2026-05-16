package com.actions;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hooks.Hooks;
import com.pages.DashBoardEmployeePage;

public class DashBoardActions {

    DashBoardEmployeePage dashBoardPage;
    WebDriverWait wait;

    public DashBoardActions() {
        dashBoardPage = new DashBoardEmployeePage();
        wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(15));
    }

    public void navigateToMyInfo() {
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.menuMyInfo));
        dashBoardPage.menuMyInfo.click();
    }

    public boolean isDashboardDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(dashBoardPage.dashboardHeader));
        return dashBoardPage.dashboardHeader.isDisplayed();
    }
}