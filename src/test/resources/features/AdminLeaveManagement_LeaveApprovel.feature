Feature: Mylambigai_18May2026_ScriptXOrangeHRM file for Approve or Reject Employee Leave Request
To review employee leave requests and either approve or reject them.

Background:
  Given the User on the Home page
  And click on the Leave menu
  And User click on  leave List and navigates to the Leave List page

@myl
Scenario: Approve employee leave request
  When User enters leave details
  And click on search button
  And  User clicks on the Approve button
  Then Leave request should be approved successfully

@myl
Scenario: Reject employee leave request
  When User enters leave details
  And click on search button
  And User clicks on the Reject button
  Then Leave request should be rejected successfully

@myl
Scenario: Invalid search by leaving mandatory field blank
  When User enters invalid data
    | From date   | To date        | Leave status          | Leave type      | Employee name |
    | 2026-01-01 |                   | Pending Approval |CAN - Personal  | Ravi M B     |
  And click on search button
  Then user get error message for blank field