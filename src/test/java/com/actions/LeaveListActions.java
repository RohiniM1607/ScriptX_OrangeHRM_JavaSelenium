package com.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pages.LeaveListPage;
import com.utilities.ConfigReader;

public class LeaveListActions {

    private static final Logger logger =
            LogManager.getLogger(LeaveListActions.class);

    LeaveListPage leaveListPage;
   
    ConfigReader leaveReader =new ConfigReader("testData.properties");
    
    public LeaveListActions() {

        leaveListPage = new LeaveListPage();
    }

    public void navigateToLeaveListPage() {

        logger.info("Navigating to Leave List page");

        leaveListPage.navigateToLeaveListPage();
    }

    public boolean verifyLeaveListPageDisplayed() {

        logger.info("Verifying Leave List page displayed");

        return leaveListPage.isLeaveListPageDisplayed();
    }

    public void filterLeaveRequestByStatus(String status) {

        logger.info("Filtering leave request by status : " + status);

        leaveListPage.selectLeaveStatus(status);

        leaveListPage.clickSearchButton();
    }

    public boolean verifySearchResultDisplayed() {

        logger.info("Verifying search result displayed");

        return leaveListPage.isSearchResultDisplayed();
    }
    
    public void filterLeaveRequestByDateRange() {

        String fromDate = leaveReader.getData("fromDate");

        String toDate = leaveReader.getData("toDate");

        leaveListPage.enterFromDate(fromDate);

        leaveListPage.enterToDate(toDate);

        leaveListPage.clickSearchButton();
    }
}