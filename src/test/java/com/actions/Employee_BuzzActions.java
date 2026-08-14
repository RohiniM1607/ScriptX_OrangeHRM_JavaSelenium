package com.actions;

import com.pages.Employee_BuzzPage;

public class Employee_BuzzActions extends BaseActions {

    private Employee_BuzzPage employeeBuzzPage =
            new Employee_BuzzPage();


    // =========================================================
    // Dashboard
    // =========================================================

    public boolean verifyDashboardDisplayed() {

        return employeeBuzzPage.isDashboardDisplayed();
    }


    // =========================================================
    // Navigate to Buzz
    // =========================================================

    public void navigateToBuzz() {

        employeeBuzzPage.clickBuzzMenu();
    }


    // =========================================================
    // Create Buzz Post
    // =========================================================

    public void enterBuzzContent(String content) {

        employeeBuzzPage.enterBuzzContent(content);
    }


    public void clickPostButton() {

        employeeBuzzPage.clickPostButton();
    }


    // =========================================================
    // Verify Created
    // =========================================================

    public boolean verifyPostCreated(String content) {

        return employeeBuzzPage.isPostDisplayed(content);
    }


    // =========================================================
    // Get Post Count
    // =========================================================

    public int getBuzzPostCount() {

        return employeeBuzzPage.getPostCount();
    }


    // =========================================================
    // Edit Buzz Post
    // =========================================================

    public void editBuzzPost(
            String content,
            String updatedContent) {

        employeeBuzzPage.editSpecificPost(
                content,
                updatedContent
        );
    }


    // =========================================================
    // Verify Updated
    // =========================================================

    public boolean verifyPostUpdated(
            String oldContent,
            String updatedContent) {

        return employeeBuzzPage.isPostUpdated(
                oldContent,
                updatedContent
        );
    }


    // =========================================================
    // Delete Buzz Post
    // =========================================================

    public void deleteBuzzPost(String content) {

        employeeBuzzPage.deleteSpecificPost(content);
    }


    // =========================================================
    // Verify Deleted
    // =========================================================

    public boolean verifyPostDeleted(String content) {

        return employeeBuzzPage.isPostDeleted(content);
    }
}