Feature: Mylambigai_18May2026_ScriptXOrangeHRM file for Approve or Reject Employee Leave Request

To review employee leave requests and either approve or reject them.

 Background:
    Given the User on the Home page
    And click on the Leave menu
    And User navigates to the Leave List page
    When User set the date from "2026-01-01" to "2026-12-31" 

@myl @Approve_leave
  Scenario: Approve employee leave request
   And selects a pending leave request and click on search button
    And User clicks on the Approve button
    Then Leave request should be approved successfully

@myl @Reject_leave
  Scenario: Reject employee leave request
   And selects a pending leave request
    And User clicks on the Reject button
    Then Leave request should be rejected successfully

@myl @LeaveList
  Scenario: Verify approved status in Leave List
     And selects a Sheduled leave status
    When User views the Leave List
    Then The leave status should be displayed as "Approved"

@myl @LeaveList
  Scenario: Verify rejected status in Leave List
    And selects a Rejected leave status
    When User views the Leave List
    Then The leave status should be displayed as "Rejected"
