@Rohini @LeaveList
Feature: Rohini_01Jun2026_OrangeHRM_LeaveList

Description:
This feature verifies whether Admin can search and filter
employee leave requests in OrangeHRM.

  Background:
    Given user is on OrangeHRM login page
    When user enters valid username and password
    And user clicks on login button
    Then user should be navigated to dashboard page
    Given user is on Leave List page

  @Rohini @Filter_By_Status
  Scenario Outline: Filter leave request by leave status
    When user filters leave request by status "<status>"
    Then leave requests with "<status>" should be displayed

    Examples:
      | status           |
      | Pending Approval |
      | Scheduled        |
      | Taken            |
