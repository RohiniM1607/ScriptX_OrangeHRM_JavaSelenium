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
    
  @Valid
  Scenario Outline: Create user login credential
    When user clicks on Add button
    And user enters user credential details "<role>" "<employeeName>" "<status>" "<username>" "<password>" "<confirmPassword>"
    And user clicks on Save button
    Then user credential should be created successfully

    Examples:
      | role  | employeeName | status  | username   | password     | confirmPassword |
      | ESS   | Employee 1   | Enabled | Employee_1 | Employee@123 | Employee@123    |
      | Admin | Admin 1      | Enabled | Admin_1    | Admin@123    | Admin@123       |
      
  @Without_Mandatory_Field
  Scenario: Create user without mandatory fields 
  When user clicks on Add button 
  And user clicks on Save button without entering mandatory fields 
  And user clicks on Save button
  Then required validation message should be displayed for mandatory fields