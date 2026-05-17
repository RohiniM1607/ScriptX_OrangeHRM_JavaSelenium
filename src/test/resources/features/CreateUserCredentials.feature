Feature: Rohini_14May2026_OrangeHRM_Create User Credential

  Description:
  This feature verifies whether Admin can create user login credentials
  for ESS employee and Admin user in OrangeHRM.

  Background:
    Given user is on OrangeHRM login page
    When user enters valid username and password
    And user clicks on login button
    Then user should be navigated to dashboard page
    Given user is on OrangeHRM Admin User Management page

  Scenario Outline: Create user login credential
    When user clicks on Add button
    And user enters user credential details "<role>" "<employeeName>" "<status>" "<username>" "<password>" "<confirmPassword>"
    And user clicks on Save button
    Then user credential should be created successfully

    Examples:
      | role  | employeeName | status  | username | password   | confirmPassword |
      | Admin | Rohini M     | Enabled | Rohini16 | Rohini@123 | Rohini@123      |